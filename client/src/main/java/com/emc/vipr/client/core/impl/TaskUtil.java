package com.emc.vipr.client.core.impl;

import com.emc.storageos.model.RestLinkRep;
import com.emc.storageos.model.TaskResourceRep;
import com.emc.storageos.model.errorhandling.ServiceErrorRestRep;
import com.emc.vipr.client.exceptions.ServiceErrorException;
import com.emc.vipr.client.exceptions.ServiceErrorsException;
import com.emc.vipr.client.exceptions.TimeoutException;
import com.emc.vipr.client.exceptions.ViPRException;
import com.emc.vipr.client.impl.RestClient;

import java.util.ArrayList;
import java.util.List;

public class TaskUtil {
    // TODO: Should introduce an enum into task object
    public static final String PENDING_STATE = "pending";
    public static final String ERROR_STATE = "error";

    public static TaskResourceRep refresh(RestClient client, TaskResourceRep task) {
        RestLinkRep link = task.getRestLink();
        if (link == null) {
            throw new ViPRException("Task has no link");
        }
        return client.get(TaskResourceRep.class, link.getLinkRef().toString());
    }

    public static TaskResourceRep waitForTask(RestClient client, TaskResourceRep task, long timeoutMillis) {
        long startTime = System.currentTimeMillis();
        while (isRunning(task)) {
            if (timeoutMillis > 0 && (System.currentTimeMillis() - startTime) > timeoutMillis) {
                throw new TimeoutException("Timed out waiting for task to complete");
            }
            try {
                Thread.sleep(client.getConfig().getTaskPollingInterval());
            }
            catch (InterruptedException e) {
                throw new ViPRException(e);
            }
            task = refresh(client, task);
        }
        return task;
    }

    public static List<TaskResourceRep> waitForTasks(RestClient client, List<TaskResourceRep> tasks, long timeoutMillis) {
        List<TaskResourceRep> newTasks = new ArrayList<TaskResourceRep>();
        for (TaskResourceRep task: tasks) {
            newTasks.add(waitForTask(client, task, timeoutMillis));
        }
        return newTasks;
    }

    public static boolean isRunning(TaskResourceRep task) {
        return PENDING_STATE.equalsIgnoreCase(task.getState());
    }

    public static boolean isComplete(TaskResourceRep task) {
        return !isRunning(task);
    }

    public static boolean isError(TaskResourceRep task) {
        return task.getState() == null || ERROR_STATE.equalsIgnoreCase(task.getState());
    }

    /**
     * Checks a task state to see if it is in error. If it is, throws an
     * exception.
     *
     * @param task Task to check for errors on
     */
    public static void checkForError(TaskResourceRep task) {
        if (isError(task)) {
            throw new ServiceErrorException(taskToError(task));
        }
    }

    public static void checkForErrors(List<TaskResourceRep> tasks) {
        List<ServiceErrorRestRep> errors = new ArrayList<ServiceErrorRestRep>();
        for (TaskResourceRep task: tasks) {
            if (isError(task)) {
                errors.add(taskToError(task));
            }
        }
        if (errors.size() == 1) {
            throw new ServiceErrorException(errors.get(0));
        }
        else if (errors.size() > 1) {
            throw new ServiceErrorsException(errors);
        }
    }

    private static ServiceErrorRestRep taskToError(TaskResourceRep task) {
        ServiceErrorRestRep serviceError = task.getServiceError();
        if (task.getState() == null) {
            serviceError = new ServiceErrorRestRep();
            serviceError.setCodeDescription("Task state is null. Unable to determine success of task");
            serviceError.setDetailedMessage("");
        }
        else if (serviceError == null) {
            serviceError = new ServiceErrorRestRep();
            serviceError.setCodeDescription(task.getMessage() == null ? "No Message" : task.getMessage());
            serviceError.setDetailedMessage("");
        }
        return serviceError;
    }
}
