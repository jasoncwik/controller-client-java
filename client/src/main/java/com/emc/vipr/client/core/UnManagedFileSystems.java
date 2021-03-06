package com.emc.vipr.client.core;

import static com.emc.vipr.client.core.util.ResourceUtils.defaultList;

import java.net.URI;
import java.util.List;

import com.emc.storageos.model.BulkIdParam;
import com.emc.storageos.model.NamedRelatedResourceRep;
import com.emc.storageos.model.RelatedResourceRep;
import com.emc.storageos.model.file.FileSystemIngest;
import com.emc.storageos.model.file.NamedFileSystemList;
import com.emc.storageos.model.file.UnManagedFileBulkRep;
import com.emc.storageos.model.file.UnManagedFileSystemList;
import com.emc.storageos.model.file.UnManagedFileSystemRestRep;
import com.emc.vipr.client.ViPRCoreClient;
import com.emc.vipr.client.core.filters.ResourceFilter;
import com.emc.vipr.client.core.impl.PathConstants;
import com.emc.vipr.client.impl.RestClient;
import com.emc.vipr.client.core.util.ResourceUtils;

/**
 * Unmanaged Filesystems resources.
 * <p>
 * Base URL: <tt>/vdc/unmanaged/filesystems</tt>
 */
public class UnManagedFileSystems extends AbstractBulkResources<UnManagedFileSystemRestRep> {
    public UnManagedFileSystems(ViPRCoreClient parent, RestClient client) {
        super(parent, client, UnManagedFileSystemRestRep.class, PathConstants.UNMANAGED_FILESYSTEMS_URL);
    }

    @Override
    public UnManagedFileSystems withInactive(boolean inactive) {
        return (UnManagedFileSystems) super.withInactive(inactive);
    }

    @Override
    protected List<UnManagedFileSystemRestRep> getBulkResources(BulkIdParam input) {
        UnManagedFileBulkRep response = client.post(UnManagedFileBulkRep.class, input, getBulkUrl());
        return defaultList(response.getUnManagedFileSystems());
    }

    /**
     * Lists the unmanaged file systems for the given storage system by ID.
     * <p>
     * API Call: <tt>GET /vdc/storage-systems/{storageSystemId}/unmanaged/filesystems</tt>
     * 
     * @param storageSystemId
     *        the ID of the storage system.
     * @return the list of unmanaged file system references.
     */
    public List<RelatedResourceRep> listByStorageSystem(URI storageSystemId) {
        UnManagedFileSystemList response = client.get(UnManagedFileSystemList.class,
                PathConstants.UNMANAGED_FILESYSTEM_BY_STORAGE_SYSTEM_URL, storageSystemId);
        return ResourceUtils.defaultList(response.getUnManagedFileSystem());
    }

    /**
     * Gets the list of unmanaged file systems for the given storage system by ID. This is a convenience method for:
     * <tt>getByRefs(listByStorageSystem(storageSystemId))</tt>
     * 
     * @param storageSystemId
     *        the ID of the storage system.
     * @return the list of unmanaged file systems.
     */
    public List<UnManagedFileSystemRestRep> getByStorageSystem(URI storageSystemId) {
        return getByStorageSystem(storageSystemId, null);
    }

    /**
     * Gets the list of unmanaged file systems for the given storage system by ID, optionally filtering the results.
     * This is a convenience method for: <tt>getByRefs(listByStorageSystem(storageSystemId), filter)</tt>
     * 
     * @param storageSystemId
     *        the ID of the storage system.
     * @param filter
     *        the resource filter to apply to the results as they are returned (optional).
     * @return the list of unmanaged file systems.
     */
    public List<UnManagedFileSystemRestRep> getByStorageSystem(URI storageSystemId,
            ResourceFilter<UnManagedFileSystemRestRep> filter) {
        List<RelatedResourceRep> refs = listByStorageSystem(storageSystemId);
        return getByRefs(refs, filter);
    }

    /**
     * Ingests unmanaged file systems.
     * <p>
     * API Call: <tt>POST /vdc/unmanaged/filesystems/ingest</tt>
     * 
     * @param input
     *        the ingest configuration.
     * @return the list of ingested file system references.
     */
    public List<NamedRelatedResourceRep> ingest(FileSystemIngest input) {
        NamedFileSystemList response = client.post(NamedFileSystemList.class, input, baseUrl + "/ingest");
        return defaultList(response.getFilesystems());
    }
}
