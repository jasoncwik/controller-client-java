package com.emc.vipr.client.core;

import static com.emc.vipr.client.core.util.ResourceUtils.defaultList;

import java.net.URI;
import java.util.List;

import com.emc.storageos.model.BulkIdParam;
import com.emc.storageos.model.NamedRelatedResourceRep;
import com.emc.storageos.model.host.vcenter.VcenterDataCenterBulkRep;
import com.emc.storageos.model.host.vcenter.VcenterDataCenterCreate;
import com.emc.storageos.model.host.vcenter.VcenterDataCenterList;
import com.emc.storageos.model.host.vcenter.VcenterDataCenterRestRep;
import com.emc.storageos.model.host.vcenter.VcenterDataCenterUpdate;
import com.emc.vipr.client.ViPRCoreClient;
import com.emc.vipr.client.core.impl.PathConstants;
import com.emc.vipr.client.impl.RestClient;
import com.emc.vipr.client.core.util.ResourceUtils;

/**
 * Virtual Data Centers resources.
 * <p>
 * Base URL: <tt>/compute/vcenter-data-centers</tt>
 */
public class VcenterDataCenters extends AbstractBulkResources<VcenterDataCenterRestRep> {
    public VcenterDataCenters(ViPRCoreClient parent, RestClient client) {
        super(parent, client, VcenterDataCenterRestRep.class, PathConstants.DATACENTER_URL);
    }

    @Override
    public VcenterDataCenters withInactive(boolean inactive) {
        return (VcenterDataCenters) super.withInactive(inactive);
    }

    @Override
    protected List<VcenterDataCenterRestRep> getBulkResources(BulkIdParam input) {
        VcenterDataCenterBulkRep response = client.post(VcenterDataCenterBulkRep.class, input, getBulkUrl());
        return defaultList(response.getVcenterDataCenters());
    }

    /**
     * Lists the datacenters for the given vCenter by ID.
     * <p>
     * API Call: <tt>GET /compute/vcenters/{vcenterId}/vcenter-data-centers</tt>
     * 
     * @param vcenterId
     *        the ID of the vCenter.
     * @return the list of datacenter references.
     */
    public List<NamedRelatedResourceRep> listByVcenter(URI vcenterId) {
        VcenterDataCenterList response = client.get(VcenterDataCenterList.class, PathConstants.DATACENTER_BY_VCENTER,
                vcenterId);
        return ResourceUtils.defaultList(response.getDataCenters());
    }

    /**
     * Gets the list of datacenters for the given vCenter by ID. This is a convenience method for:
     * <tt>getByRefs(listByVcenter(vcenterId))</tt>
     * 
     * @param vcenterId
     *        the ID of the vCenter.
     * @return the list of datacenters.
     */
    public List<VcenterDataCenterRestRep> getByVcenter(URI vcenterId) {
        List<NamedRelatedResourceRep> refs = listByVcenter(vcenterId);
        return getByRefs(refs, null);
    }

    /**
     * Creates a datacenter within the given vCenter by ID.
     * <p>
     * API Call: <tt>POST /compute/vcenters/{vcenterId}/vcenter-data-centers</tt>
     * 
     * @param vcenterId
     *        the ID of the vCenter.
     * @param input
     *        the create configuration.
     * @return the newly created datacenter.
     */
    public VcenterDataCenterRestRep create(URI vcenterId, VcenterDataCenterCreate input) {
        return client.post(VcenterDataCenterRestRep.class, input, PathConstants.DATACENTER_BY_VCENTER, vcenterId);
    }

    /**
     * Updates the given datacenter by ID.
     * <p>
     * API Call: <tt>PUT /compute/vcenter-data-centers/{id}</tt>
     * 
     * @param id
     *        the ID of the datacenter to update.
     * @param input
     *        the update configuration.
     * @return the updated datacenter.
     */
    public VcenterDataCenterRestRep update(URI id, VcenterDataCenterUpdate input) {
        return client.put(VcenterDataCenterRestRep.class, input, getIdUrl(), id);
    }

    /**
     * Deactivates the given datacenter by ID.
     * <p>
     * API Call: <tt>POST /compute/vcenter-data-centers/{id}/deactivate</tt>
     * 
     * @param id
     *        the ID of the datacenter to deactivate.
     */
    public void deactivate(URI id) {
        doDeactivate(id);
    }
}
