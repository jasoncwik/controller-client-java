package com.emc.vipr.client.core;

import static com.emc.vipr.client.core.util.ResourceUtils.defaultList;

import java.net.URI;
import java.util.List;

import com.emc.storageos.model.BulkIdParam;
import com.emc.storageos.model.NamedRelatedResourceRep;
import com.emc.storageos.model.host.vcenter.VcenterBulkRep;
import com.emc.storageos.model.host.vcenter.VcenterCreateParam;
import com.emc.storageos.model.host.vcenter.VcenterList;
import com.emc.storageos.model.host.vcenter.VcenterRestRep;
import com.emc.storageos.model.host.vcenter.VcenterUpdateParam;
import com.emc.vipr.client.Task;
import com.emc.vipr.client.Tasks;
import com.emc.vipr.client.ViPRCoreClient;
import com.emc.vipr.client.core.filters.ResourceFilter;
import com.emc.vipr.client.core.impl.PathConstants;
import com.emc.vipr.client.impl.RestClient;
import com.emc.vipr.client.core.util.ResourceUtils;

/**
 * Vcenters resources.
 * <p>
 * Base URL: <tt>/compute/vcenters</tt>
 */
public class Vcenters extends AbstractBulkResources<VcenterRestRep> implements TenantResources<VcenterRestRep>,
        TaskResources<VcenterRestRep> {
    public Vcenters(ViPRCoreClient parent, RestClient client) {
        super(parent, client, VcenterRestRep.class, PathConstants.VCENTER_URL);
    }

    @Override
    public Vcenters withInactive(boolean inactive) {
        return (Vcenters) super.withInactive(inactive);
    }

    @Override
    protected List<VcenterRestRep> getBulkResources(BulkIdParam input) {
        VcenterBulkRep response = client.post(VcenterBulkRep.class, input, getBulkUrl());
        return defaultList(response.getVcenters());
    }

    @Override
    public Tasks<VcenterRestRep> getTasks(URI id) {
        return doGetTasks(id);
    }

    @Override
    public Task<VcenterRestRep> getTask(URI id, URI taskId) {
        return doGetTask(id, taskId);
    }

    /**
     * Gets a list of vCenters from the given path.
     * 
     * @param path
     *        the path to get.
     * @param args
     *        the path arguments.
     * @return the list of vCenter references.
     */
    protected List<NamedRelatedResourceRep> getList(String path, Object... args) {
        VcenterList response = client.get(VcenterList.class, path, args);
        return ResourceUtils.defaultList(response.getVcenters());
    }

    /**
     * Lists the vCenters for the given tenant by ID.
     * <p>
     * API Call: <tt>GET /tenants/{tenantId}/vcenters</tt>
     * 
     * @param tenantId
     *        the ID of the tenant.
     * @return the list of vCenter references.
     */
    @Override
    public List<NamedRelatedResourceRep> listByTenant(URI tenantId) {
        return getList(PathConstants.VCENTER_BY_TENANT_URL, tenantId);
    }

    @Override
    public List<VcenterRestRep> getByTenant(URI tenantId) {
        return getByTenant(tenantId, null);
    }

    @Override
    public List<NamedRelatedResourceRep> listByUserTenant() {
        return listByTenant(parent.getUserTenantId());
    }

    @Override
    public List<VcenterRestRep> getByUserTenant() {
        return getByTenant(parent.getUserTenantId());
    }

    @Override
    public List<VcenterRestRep> getByUserTenant(ResourceFilter<VcenterRestRep> filter) {
        return getByTenant(parent.getUserTenantId(), filter);
    }

    @Override
    public List<VcenterRestRep> getByTenant(URI tenantId, ResourceFilter<VcenterRestRep> filter) {
        List<NamedRelatedResourceRep> refs = listByTenant(tenantId);
        return getByRefs(refs, filter);
    }

    /**
     * Begins creating a vCenter for the given tenant by ID.
     * <p>
     * API Call: <tt>POST /tenants/{tenantId}/vcenters</tt>
     * 
     * @param tenantId
     *        the ID of the tenant.
     * @param input
     *        the create configuration.
     * @return a task for monitoring the progress of the operation.
     */
    public Task<VcenterRestRep> create(URI tenantId, VcenterCreateParam input) {
        return postTask(input, PathConstants.VCENTER_BY_TENANT_URL, tenantId);
    }

    /**
     * Begins updating the given vCenter by ID.
     * <p>
     * API Call: <tt>PUT /compute/vcenter/{id}</tt>
     * 
     * @param id
     *        the ID of the vCenter.
     * @param input
     *        the update configuration.
     * @return a task for monitoring the progress of the operation.
     */
    public Task<VcenterRestRep> update(URI id, VcenterUpdateParam input) {
        return putTask(input, getIdUrl(), id);
    }

    /**
     * Deactivates the given vCenter by ID.
     * <p>
     * API Call: <tt>POST /compute/vcenter/{id}/deactivate</tt>
     * 
     * @param id
     *        the ID of the vCenter to deactivate.
     */
    public void deactivate(URI id) {
        doDeactivate(id);
    }
}