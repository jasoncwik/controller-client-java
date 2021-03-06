package com.emc.vipr.client.core;

import static com.emc.vipr.client.core.util.ResourceUtils.defaultList;

import java.net.URI;
import java.util.List;

import com.emc.storageos.model.BulkIdParam;
import com.emc.storageos.model.NamedRelatedResourceRep;
import com.emc.storageos.model.SnapshotList;
import com.emc.storageos.model.file.FileSnapshotBulkRep;
import com.emc.storageos.model.file.FileSnapshotRestRep;
import com.emc.storageos.model.file.FileSystemExportList;
import com.emc.storageos.model.file.FileSystemExportParam;
import com.emc.storageos.model.file.FileSystemShareList;
import com.emc.storageos.model.file.FileSystemShareParam;
import com.emc.storageos.model.file.FileSystemSnapshotParam;
import com.emc.storageos.model.file.SmbShareResponse;
import com.emc.vipr.client.Task;
import com.emc.vipr.client.Tasks;
import com.emc.vipr.client.ViPRCoreClient;
import com.emc.vipr.client.core.filters.ResourceFilter;
import com.emc.vipr.client.core.impl.PathConstants;
import com.emc.vipr.client.impl.RestClient;

/**
 * File Snapshots resources.
 * <p>
 * Base URL: <tt>/file/snapshots</tt>
 */
public class FileSnapshots extends AbstractBulkResources<FileSnapshotRestRep> implements
        TaskResources<FileSnapshotRestRep> {
    public FileSnapshots(ViPRCoreClient parent, RestClient client) {
        super(parent, client, FileSnapshotRestRep.class, PathConstants.FILE_SNAPSHOT_URL);
    }

    @Override
    public FileSnapshots withInactive(boolean inactive) {
        return (FileSnapshots) super.withInactive(inactive);
    }

    /**
     * Gets the base URL for exports for a single snapshot: <tt>/file/snapshots/{id}/exports</tt>
     * 
     * @return the exports URL.
     */
    protected String getExportsUrl() {
        return getIdUrl() + "/exports";
    }

    /**
     * Gets the base URL for shares for a single snapshot: <tt>/file/snapshots/{id}/shares</tt>
     * 
     * @return the shares URL.
     */
    protected String getSharesUrl() {
        return getIdUrl() + "/shares";
    }

    @Override
    protected List<FileSnapshotRestRep> getBulkResources(BulkIdParam input) {
        FileSnapshotBulkRep response = client.post(FileSnapshotBulkRep.class, input, getBulkUrl());
        return defaultList(response.getFileSnapshots());
    }

    @Override
    public Tasks<FileSnapshotRestRep> getTasks(URI id) {
        return doGetTasks(id);
    }

    @Override
    public Task<FileSnapshotRestRep> getTask(URI id, URI taskId) {
        return doGetTask(id, taskId);
    }

    /**
     * Begins restoring a file snapshot by ID.
     * <p>
     * API Call: <tt>POST /file/snapshots/{id}/restore</tt>
     * 
     * @param id
     *        the ID of the file snapshot to restore.
     * @return a task for monitoring the progress of the operation.
     */
    public Task<FileSnapshotRestRep> restore(URI id) {
        return postTask(getIdUrl() + "/restore", id);
    }

    /**
     * Begins deactivating a file snapshot by ID.
     * <p>
     * API Call: <tt>POST /file/snapshots/{id}/deactivate</tt>
     * 
     * @param id
     *        the ID of the file snapshot to deactivate.
     * @return a task for monitoring the progress of the operation.
     */
    public Task<FileSnapshotRestRep> deactivate(URI id) {
        return doDeactivateWithTask(id);
    }

    /**
     * Gets the exports for the given file snapshot by ID.
     * <p>
     * API Call: <tt>GET /file/snapshots/{id}/exports</tt>
     * 
     * @param id
     *        the ID of the file snapshot.
     * @return the list of exports for the snapshot.
     */
    public List<FileSystemExportParam> getExports(URI id) {
        FileSystemExportList response = client.get(FileSystemExportList.class, getExportsUrl(), id);
        return defaultList(response.getExportList());
    }

    /**
     * Begins exporting a given file snapshot by ID.
     * <p>
     * API Call: <tt>POST /file/snapshots/{id}/exports</tt>
     * 
     * @param id
     *        the ID of the file snapshot to export.
     * @param input
     *        the export configuration.
     * @return a task for monitoring the progress of the operation.
     */
    public Task<FileSnapshotRestRep> export(URI id, FileSystemExportParam input) {
        return postTask(input, getExportsUrl(), id);
    }

    /**
     * Removes a single export from a file snapshot by ID.
     * <p>
     * API Call: <tt>DELETE /file/snapshots/{id}/exports/{protocol},{securityType},{permissions},{rootUserMapping}</tt>
     * 
     * @param id
     *        the ID of the file snapshot.
     * @param protocol
     *        the protocol of the export to remove.
     * @param securityType
     *        the security type of the export to remove.
     * @param permissions
     *        the permissions of the export to remove.
     * @param rootUserMapping
     *        the root user mapping of the export to remove.
     * @return a task for monitoring the progress of the operation.
     */
    public Task<FileSnapshotRestRep> removeExport(URI id, String protocol, String securityType, String permissions,
            String rootUserMapping) {
        return deleteTask(getExportsUrl() + "/{protocol},{securityType},{permissions},{rootUserMapping}", id, protocol,
                securityType, permissions, rootUserMapping);
    }

    /**
     * Gets the shares for the given file snapshot by ID.
     * <p>
     * API Call: <tt>GET /file/snapshots/{id}/shares</tt>
     * 
     * @param id
     *        the ID of the file snapshot.
     * @return the list of shares for the file snapshot.
     */
    public List<SmbShareResponse> getShares(URI id) {
        FileSystemShareList response = client.get(FileSystemShareList.class, getSharesUrl(), id);
        return defaultList(response.getShareList());
    }

    /**
     * Begins sharing a file snapshot by ID.
     * <p>
     * API Call: <tt>POST /file/snapshots/{id}/shares</tt>
     * 
     * @param id
     *        the ID of the file snapshot.
     * @param input
     *        the share configuration.
     * @return a task for monitoring the progress of the operation.
     */
    public Task<FileSnapshotRestRep> share(URI id, FileSystemShareParam input) {
        return postTask(input, getSharesUrl(), id);
    }

    /**
     * Removes a share of the given file snapshot by ID.
     * <p>
     * API Call: <tt>DELETE /file/snapshots/{id}/shares/{shareName}</tt>
     * 
     * @param id
     *        the ID of the file snapshot.
     * @param shareName
     *        the name of the share to remove.
     * @return a task for monitoring the progress of the operation.
     */
    public Task<FileSnapshotRestRep> removeShare(URI id, String shareName) {
        return deleteTask(getSharesUrl() + "/{shareName}", id, shareName);
    }

    /**
     * Gets the base URL for finding snapshots by file system:
     * <tt>/file/filesystems/{fileSystemId}/protection/snapshots</tt>
     * 
     * @return the URL for finding by file system.
     */
    protected String getByFileSystemUrl() {
        return PathConstants.FILESYSTEM_URL + "/{fileSystemId}/protection/snapshots";
    }

    /**
     * Lists the file snapshots for the given file system by ID.
     * <p>
     * API Call: <tt>GET /file/filesystems/{fileSystemId}/protection/snapshots</tt>
     * 
     * @param fileSystemId
     *        the ID of the file system.
     * @return the list of file snapshot references for the file system.
     */
    public List<NamedRelatedResourceRep> listByFileSystem(URI fileSystemId) {
        SnapshotList response = client.get(SnapshotList.class, getByFileSystemUrl(), fileSystemId);
        return defaultList(response.getSnapList());
    }

    /**
     * Gets the list of file snapshots for the given file system by ID.
     * <p>
     * This is a convenience method for: <tt>getByRefs(listByFileSystem(fileSystemId))</tt>
     * 
     * @param fileSystemId
     *        the ID of the file system.
     * @return the list of file snapshots for the file system.
     */
    public List<FileSnapshotRestRep> getByFileSystem(URI fileSystemId) {
        return getByFileSystem(fileSystemId, null);
    }

    /**
     * Gets the list of file snapshots for the given file system by ID, optionally filtering the results.
     * <p>
     * This is a convenience method for: <tt>getByRefs(listByFileSystem(fileSystemId), filter)</tt>
     * 
     * @param fileSystemId
     *        the ID of the file system.
     * @param filter
     *        the resource filter to apply to the results as they are returned (optional).
     * @return the list of file snapshots for the file system.
     */
    public List<FileSnapshotRestRep> getByFileSystem(URI fileSystemId, ResourceFilter<FileSnapshotRestRep> filter) {
        List<NamedRelatedResourceRep> refs = listByFileSystem(fileSystemId);
        return getByRefs(refs, filter);
    }

    /**
     * Creates a file snapshot for the given file system by ID.
     * <p>
     * API Call: <tt>POST /block/volumes/{volumeId}/protection/snapshots</tt>
     * 
     * @param fileSystemId
     *        the ID of the file system.
     * @param input
     *        the create configuration.
     * @return tasks for monitoring the progress of the operation(s).
     */
    public Task<FileSnapshotRestRep> createForFileSystem(URI fileSystemId, FileSystemSnapshotParam input) {
        return postTask(input, getByFileSystemUrl(), fileSystemId);
    }
}
