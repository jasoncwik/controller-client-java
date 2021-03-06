/**
 *  Copyright (c) 2008-2011 EMC Corporation
 * All Rights Reserved
 *
 * This software contains the intellectual property of EMC Corporation
 * or is licensed to EMC Corporation from third parties.  Use of this
 * software and the intellectual property contained therein is expressly
 * limited to the terms and conditions of the License Agreement under which
 * it is provided by or on behalf of EMC.
 */
package com.emc.storageos.model;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;

public enum ResourceOperationTypeEnum {

    //@todo - update the following enum with more fields (esp description, audit message, event messages(if needed)
    //@todo- validate the actions/dessription (esp controller specific operation) with controller team.

    /*                          name                        description               */
    CREATE_TENANT           ("CREATE TENANT",           "create tenant operation."),
    UPDATE_TENANT           ("UPDATE TENANT",           "update tenant operation."),
    DELETE_TENANT           ("DELETE TENANT",           "delete tenant operaton."),
    UPDATE_TENANT_STS       ("UPDATE TENANT STS",       "update tenant's STS operation."),
    REASSIGN_TENANT_ROLES   ("REPLACE TENANT ROLES",    "operation to overwrite tenant roles."),
    MODIFY_TENANT_ROLES     ("MODIFY TENANT ROLES",     "operation to modify the tenant roles."),
    CREATE_TENANT_TAG       ("TAG TENANT",              "operation to tag on a tenant."),
    REASSIGN_ZONE_ROLES     ("REPLACE ZONE ROLES",      "operation to overwrite zone roles."),
    MODIFY_ZONE_ROLES       ("MODIFY ZONE ROLES",       "operation to modify the zone roles."),
    CREATE_PROJECT          ("CREATE PROJECT",          "create project."),
    UPDATE_PROJECT          ("UPDATE PROJECT",          "update project"),
    DELETE_PROJECT          ("DELETE PROJECT",          "delete project operation"),
    MODIFY_PROJECT_ACL      ("MODIFY PROJECT ACL",      "operation to modify project acls"),
    REASSIGN_PROJECT_ACL    ("REPLACE PROJECT ACL",     "operation to overwrite project acls"),
    CREATE_VPOOL            ("CREATE VPOOL",            "create vpool operation."),
    UPDATE_VPOOL            ("UPDATE VPOOL",            "update vpool operation."),
    DELETE_VPOOL            ("DELETE VPOOL",            "delete vpool operation."),
    REASSIGN_VPOOL_ACL      ("REPLACE VPOOL ACL",         "operation to overwrite vpool acls"),
    MODIFY_VPOOL_ACL        ("MODIFY VPOOL ACL",          "operation to modify vpool acls"),
    ASSIGN_VPOOL_TAG        ("ASSIGN VPOOL TAG",          "operation to assign a tag to a vpool"),
    CREATE_BLOCK_VOLUME     ("CREATE VOLUME",           "create volume operation"),
    DELETE_BLOCK_VOLUME     ("DELETE VOLUME",           "delete operation"),
    EXPAND_BLOCK_VOLUME     ("EXPAND VOLUME",           "expand volume operation"),
    IMPORT_BLOCK_VOLUME     ("IMPORT BLOCK VOLULME",    "operation to import block volume"),
    MIGRATE_BLOCK_VOLUME    ("MIGRATE VOLUME",          "migrate volume operation"),
    MIGRATE_EXPAND_BLOCK_VOLUME    ("MIGRATE EXPAND VOLUME",          "operation to migrate volume for expansion"),
    COMMIT_VOLUME_MIGRATION            ("COMMIT VOLUME MIGRATION",        "operation to commit volume migration"),
    ROLLBACK_COMMIT_VOLUME_MIGRATION   ("ROLLBAK COMMIT VOLUME MIGRATION",  "operation to rollback commit volume migration"),
    DELETE_MIGRATION_SOURCE_VOLUMES    ("DELETE MIGRATION SOURCE VOLUMES",  "operation to delete migration source volumes"),
    CREATE_VOLUME_WORKFLOW_STEP ("CREATE VOLUME WF STEP",           "create volume workflow step"),
    DELETE_VOLUME_WORKFLOW_STEP ("DELETE VOLUME WF STEP",           "delete volume workflow step"),
    ADD_INITIATOR_WORKFLOW_STEP ("ADD INITIATOR WF STEP",           "add initiator workflow step"),
    DELETE_INITIATOR_WORKFLOW_STEP ("DELETE INITIATOR WF STEP",     "delete initiator workflow step"),
    CREATE_VOLUME_FULL_COPY ("CREATE VOLUME FULL COPY", "operation to create a volume full copy"),
    ACTIVATE_VOLUME_FULL_COPY ("ACTIVATE VOLUME FULL COPY", "operation to activate a volume full copy"),
    DETACH_VOLUME_FULL_COPY ("DETACH VOLUME FULL COPY", "operation to detach a volume full copy"),
    CREATE_VOLUME_SNAPSHOT  ("CREATE VOLUME SNAPSHOT",  "operation to create a volume snapshot"),
    ASSIGN_VOLUME_TAG       ("ASSIGN VOLUME TAG",       "operation to tag a volume"),
    DELETE_VOLUME_SNAPSHOT  ("DELETE VOLUME SNAPSHOT",  "operation to delete volume snapshot"),
    RESTORE_VOLUME_SNAPSHOT ("RESTORE VOLUME SNAPSHOT", "operation to restore volume snapshot"),
    ACTIVATE_VOLUME_SNAPSHOT("ACTIVATE VOLUME SNAPSHOT","operation to activate volume snapshot"),
    DEACTIVATE_VOLUME_SNAPSHOT("DEACTIVATE VOLUME SNAPSHOT","operation to deactivate volume snapshot"),
    ASSIGN_VOLUME_SNAPSHOT_TAG ("TAG VOLUME SNAPSHOT",  "operation to tag a volume snapshot"),
    CREATE_EXPORT_GROUP        ("CREATE EXPORT GROUP",  "create export group operation"),
    DELETE_EXPORT_GROUP        ("DELETE EXPORT GROUP",  "delete export group operation"),
    ADD_EXPORT_VOLUME          ("ADD VOLUME TO EXPORT GROUP",   "add volume to export group."),
    CREATE_VIRTUAL_VOLUME   ("CREATE VIRTUAL VOLUME",   "operation to create a virtual volume"),
    ROLLBACK_CREATE_VIRTUAL_VOLUME   ("ROLLBACK CREATE VIRTUAL VOLUME",  "operation to rollback virtual volume create"),
    DELETE_VIRTUAL_VOLUME   ("DELETE VIRTUAL VOLUME",       "operation to delete a virtual volume"),
    EXPAND_VIRTUAL_VOLUME   ("EXPAND VIRTUAL VOLUME",       "operation to expand a virtual volume"),
    MIGRATE_VIRTUAL_VOLUME  ("MIGRATE VIRTUAL VOLUME",      "operation to migrate a virtual volume"),
    ROLLBACK_MIGRATE_VIRTUAL_VOLUME   ("ROLLBACK MIGRATE VIRTUAL VOLUME",  "operation to rollback virtual volume migrate"),
    CREATE_VVOLUME_FROM_IMPORT ("CREATE VIRTUAL VOLUME FROM IMPORT",       "operation to create virtual volume from imported volume"),
    UPDATE_EXPORT_GROUP        ("UPDATE EXPORT GROUP",           "update export group operation"),
    DELETE_EXPORT_VOLUME       ("DELETE VOLUME FROM EXPORTGROUP",      "delete volume from the export group"),
    CREATE_FILE_SYSTEM         ("CREATE FILESYSTEM",     "create filesystem operation"),
    DELETE_FILE_SYSTEM         ("DELETE FILESYSTEM",     "delete filesystem operation"),
    EXPORT_FILE_SYSTEM         ("EXPORT FILESYSTEM",     "export filesystem operation"),
    EXPAND_FILE_SYSTEM         ("EXPAND FILESYSTEM",     "expand filesystem operation"),
    CREATE_FILE_SYSTEM_SHARE   ("CREATE FILESHARE",      "create fileshare operation"),
    CREATE_FILE_SYSTEM_SNAPSHOT("SNAPSHOT FILESYSTEM",   "operation to snapshot filesystem"),
    ASSIGN_FILE_SYSTEM_TAG     ("TAG A FILESYSTEM",      "operation to tag a filesystem"),
    DELETE_FILE_SNAPSHOT       ("DELETE FILESYSTEM SNAPSHOT",   "operation to delete filesystem snapshot"),
    UNEXPORT_FILE_SYSTEM       ("UNEXPORT FILESYSTEM",          "operation to unexport filesystem"),
    DELETE_FILE_SYSTEM_SHARE   ("DELETE FILESHARE",             "oepration to delete fileshare"),
    EXPORT_FILE_SNAPSHOT       ("EXPORT FILESYSTEM SNAPSHOT",   "operation to export fileshare snapshot"),
    CREATE_FILE_SNAPSHOT_SHARE ("SNAPSHOT FILESHARE",           "oepration to perform fileshare snapshot"),
    ASSIGN_FILE_SNAPSHOT_TAG   ("TAG A FILESYSTEM SNAPSHOT",    "tag a fileshare snapshot"),
    UNEXPORT_FILE_SNAPSHOT     ("UNEXPORT FILESYSTEM SNAPSHOT", "operation to unexport fileshare snapshot"),
    DELETE_FILE_SNAPSHOT_SHARE ("DELETE FILESHARE SNAPSHOT",    "operation to delete fileshare snapshot"),
    RESTORE_FILE_SNAPSHOT      ("RESTORE FILE SANPSHOT",        "operation to restore fileshare snapshot"),
    CREATE_KEYPOOL             ("CREATE KEYPOOL",     "create keypool operation"),
    DELETE_KEYPOOL             ("DELETE KEYPOOL",     "delete keypool operation"),
    UPDATE_KEYPOOL_ACCESSMODE  ("UPDATE KEYPOOL",     "update keypool operation"),
    CREATEnameSPACE           ("CREATE NAMESPACE",   "create object namespace operaiton"),
    UPDATEnameSPACE           ("UPDATE NAMESPACE",   "update object namespace operaiton"),
    DELETEnameSPACE           ("DELETE NAMESPACE",   "delete object namespace operaiton"),
    CREATE_VARRAY              ("CREATE VARRAY",      "create varray operation"),
    DELETE_VARRAY              ("DELETE VARRAY",      "delete varray oepration"),
    UPDATE_VARRAY              ("UPDATE VARRAY",      "update varray oepration"),
    MODIFY_VARRAY_ACL          ("UPDATE VARRAY ACL",   "operation to update varray acls"),
    REASSIGN_VARRAY_ACL        ("REPLACE VARRAY ACL",  "operation to overwrite varray acls"),
    CREATE_NETWORK             ("CREATE NETWORK",      "create network oepration"),
    CREATE_OBJECT_POOL         ("CREATE OBJECTPOOL",     "create object pool operation"),
    DELETE_OBJECT_POOL         ("DELETE OBJECTPOOL",     "delete object pool operation"),
    CREATE_SECRET_KEY          ("CREATE SECRET KEY",     "create a secret key for a user"),
    DELETE_SECRET_KEY          ("DELETE SECRET KEY",     "delete a users secret key"),
    REGISTER_SMISPROVIDER      ("REGISTER SMIS PROVIDER","operation to register smis provider"),
    DELETE_SMISPROVIDER        ("DELETE SMIS PROVIDER",  "oepration to delete smis provider"),
    UPDATE_SMISPROVIDER        ("UPDATE SMIS PROVIDER",  "operation to update smis provider"),
    SCAN_SMISPROVIDER          ("SCAN SMIS PROVIDER",    "operation to scan smis provider"),
    REGISTER_STORAGE_SYSTEM    ("REGISTER SMIS SYSTEM","operation to register sims system"),
    UPDATE_STORAGE_POOL        ("UPDATE STORAGEPOOL",     "operation to update storage pool."),
    DELETE_STORAGE_POOL        ("DELETE STORAGEPOOL",     "operation to delete storage pool."),
    DEREGISTER_STORAGE_POOL    ("UNREGISTER STORAGEPOOL", "operation to unregister a storage pool."),
    ASSIGN_STORAGE_POOL_TAG    ("TAG STORAGEPOOL",        "operation to tag a storage pool."),
    UPDATE_STORAGE_PORT        ("UPDATE STORAGEPORT",     "operation to update storage port"),
    DELETE_STORAGE_PORT        ("DELETE STORAGEPORT",     "operation to delete storage port"),
    SELECT_STORAGE_PORT        ("SELECT STORAGEPORT",     "operation to select storage port for export"),
    DEREGISTER_STORAGE_PORT    ("UNREGISTER STORAGEPORT", "operation to unregister storage port"),
    ASSIGN_STORAGE_PORT_TAG    ("TAG STORAGEPORT",        "tag a storage port"),
    CREATE_STORAGE_SYSTEM      ("CREATE STORAGESYSTEM",   "operation to create a storage system"),
    UPDATE_STORAGE_SYSTEM      ("UPDATE STORAGESYSTEM",   "operation to update a storage system"),
    DELETE_STORAGE_SYSTEM      ("DELETE STORAGESYSTEM",   "operation to delete a storage system"),
    ADD_STORAGE_SYSTEM         ("ADD STORAGESYSTEM",   "operation to add a storage system"),
    DISCOVER_ALL_STORAGE_SYSTEM("DISCOVER ALL STORAGESYSTEMS",  "operation to discover all storage systems"),
    DISCOVER_STORAGE_SYSTEM    ("DISCOVER STORAGESYSTEM",       "operation to discover one storage systems"),
    METERING_STORAGE_SYSTEM    ("METERING STORAGE SYSTEM",      "operation to obtain metering information on a storage system"),
    DEREGISTER_STORAGE_SYSTEM  ("UNREGISTER STORAGESYSTEM",     "operation to unregister a storage systems"),
    CREATE_STORAGE_POOL        ("CREATE STORAGEPOOL",     "create storagepool operation"),
    CREATE_STORAGE_PORT        ("CREATE STORAGEPORT",     "create storageport operation"),
    ASSIGN_STORAGE_SYSTEM_TAG  ("TAG STORAGESYSTEM",      "operation to tag a storage system"),
    REGISTER_STORAGE_POOL      ("REGISTER STORAGEPOOL",   "operation to register storageport"),
    REGISTER_STORAGE_PORT      ("REGISTER STORAGEPORT",   "operation to register storage port"),
    DELETE_NETWORK             ("DELETE NETWORK",   "operation to delete network"),
    UPDATE_NETWORK             ("UPDATE NETWORK",  "operation to update network"),
    UPDATE_NETWORK_ENDPOINT    ("UPDATE NETWORK ENDPOINT", "operation to update network endpoint"),
    ASSIGN_NETWORK_TAG         ("TAG NETWORK",     "operation to tag a network"),
    CREATE_ATMOS_SUBTENANT        ("CREATE ATMOS SUBTENANT","create atmos subtenant"),
    DELETE_ATMOS_SUBTENANT        ("DELETE ATMOS SUBTENANT","delete atmos subtenant"),
    CREATE_S3_BUCKET              ("CREATE S3 BUCKET",      "create S3 bucket"),
    SET_S3_BUCKET_ACL             ("SET ACL ON S3 BUCKET",  "operation to set an acl on S3 bucket"),
    SET_S3_BUCKET_VERSION         ("VERSION S3 BUCKET",     "operation to set the version of S3 bucket"),
    CREATE_SWIFT_CONTAINER        ("CREATE SWIFT CONTAINER","operation to create swift container"),
    DELETE_SWIFT_CONTAINER        ("DELETE SWIFT CONTAINER","operation to delete swift container"),
    CREATE_INITIATOR           ("CREATE INITIATOR",         "operation to create initiator."),
    DELETE_INITIATOR           ("DELETE INITIATOR",         "operation to delete initiator."),
    CREATE_NETWORK_SYSTEM      ("CREATE NETWORKSYSTEM",     "operation to create a network system."),
    UPDATE_NETWORK_SYSTEM      ("UPDATE NETWORKSYSTEM",     "operation to update a network system."),
    DELETE_NETWORK_SYSTEM      ("DELETE NETWORKSYSTEM",     "operation to delete a network system."),
    DISCOVER_NETWORK_SYSTEM    ("DISCOVER NETWORKSYSTEM",   "operation to discover one network system."),
    ADD_SAN_ZONE             ("ADD SAN ZONE",          "operation to add a san zone."),
    REMOVE_SAN_ZONE          ("REMOVE SAN ZONE",       "operation to remove a san zone."),
    PERFORM_PROTECTION_OPERATION  ("PERFORM PROTECTION OPERATION",  "operation to protect a block volume" ),
    DISCOVER_PROTECTION_SET       ("DISCOVER_PROTECTION_SET", "operation to discover protection set"),
    PERFORM_PROTECTION_ACTION ("PERFORM PROTECTION ACTION", "operation to perform link management"),
    CREATE_AUTHPROVIDER        ("CREATE AUTH PROVIDER",     "operation to create a authentication provider."),
    UPDATE_AUTHPROVIDER        ("UPDATE AUTH PROVIDER",     "operation to update a authentication provider."),
    DELETE_AUTHPROVIDER        ("DELETE AUTH PROVIDER",     "operation to delete a authentication provider."),
    SSH_LOGIN                   ("SSH LOGIN",                "ssh login."),
    AUTHENTICATION             ("AUTHENTICATION",           "authentication"),
    UPDATE_VERSION             ("UPDATE VERSION",           "operation to upgrade."),
    INSTALL_IMAGE              ("INSTALL IMAGE",            "operation to upload a image from remote server."),
    REMOVE_IMAGE               ("REMOVE IMAGE",             "operation to remove a image."),
    UPLOAD_IMAGE               ("UPLOAD IMAGE",             "operation to uploade a image from remote server."),
    WAKEUP_UPGRAGE_MANAGER     ("WAKEUP UPGRADE MANAGER",   "operation to wakeup upgrade manager."),
    UPDATE_SYSTEM_PROPERTY     ("UPDATE SYSTEM PROPERTY",  "operation to update system property."),
    SEND_ALERT                 ("SEND ALERT",               "operation to send a alert."),
    SEND_REGISTRATION          ("SEND ALERT",               "operation to send a registration."),
    SEND_HEARTBEAT             ("SEND HEARTBEAT",           "operation to send heartbeat signal."),
    SEND_STAT                  ("SEND STAT",                "operation to send a state"),
    SEND_LICENSE_EXPIRATION    ("SEND LICENCE EXPIRATION",  "operation to inform license expiration."),
    CREATE_ESRS_CONFIGURATION       ("CREATE ESRS CONFIGURATION",       "operation to create esrs configuration."),
    SCHEDULE_EVENT                  ("SCHEDULE EVENT",                  "operation to schedule event."),
    CHANGE_LOCAL_AUTHUSER_PASSWORD  ("CHANGE LOCAL AUTHUSER PASSWORD",  "operation to change local authuser password."),
    RESET_LOCAL_USER_PASSWORD       ("RESET LOCAL AUTHUSER PASSWORD",   "operation to reset local authuser password."),
    CHANGE_LOCAL_AUTHUSER_AUTHKEY   ("CHANGE LOCAL AUTHUSER AUTHKEY",   "operation to change local authuser authkey."),
    FRACTURE_VOLUME_MIRROR          ("FRACTURE VOLUME MIRROR",          "operation to fracture a volume mirror"),
    RESUME_VOLUME_MIRROR            ("RESUME VOLUME MIRROR",            "operation to resume a volume mirror"),
    DEACTIVATE_VOLUME_MIRROR        ("DEACTIVATE VOLUME MIRROR",        "operation to deactivate a volume mirror"),
    DISCOVER_HOST                   ("DISCOVER HOST",                   "operation to discover a compute host"),
    DISCOVER_VCENTER                ("DISCOVER VCENTER",                "operation to discover a vcenter"),
    CHANGE_BLOCK_VOLUME_VPOOL       ("CHANGE VOLUME VPOOL",             "change volume vpool operation"),
    CHANGE_BLOCK_VOLUME_VARRAY      ("CHANGE VOLUME VARRAY",            "change volume varray operation"),
    CREATE_CONSISTENCY_GROUP        ("CREATE CONSISTENCY GROUP",        "operation to create a consistency group"),
    DELETE_CONSISTENCY_GROUP        ("DELETE CONSISTENCY GROUP",        "operation to delete a consistency group"),
    CREATE_VOLUME_MIRROR            ("CREATE VOLUME MIRROR",            "operation to create a volume mirror"),
    ATTACH_BLOCK_MIRROR     ("ATTACH MIRROR",           "attach mirror operation"),
    DETACH_BLOCK_MIRROR     ("DETACH MIRROR",           "detach mirror operation"),
    SET_CG_VISIBILITY               ("SET CG VISIBILITY",               "operation to set visibility of a consistency group with no volumes"),
    ADD_CG_VOLUME                   ("ADD CG VOLUME",                   "operation to add volumes to CG"),
    DELETE_CG_VOLUME                ("DELETE CG VOLUME",                "operation to delete volumes from CG"),
    CREATE_STORAGE_SYSTEM_CONNECTION    ("CREATE STORAGE SYSTEM CONNECTION", "operation to establish storage system connection"),
    DELETE_STORAGE_SYSTEM_CONNECTION    ("DELETE STORAGE SYSTEM CONNECTION", "operation to terminate storage system connection"),
    UPDATE_CONSISTENCY_GROUP        ("UPDATE CONSISTENCY GROUP",        "operation to update a consistency group"),
    CREATE_CONSISTENCY_GROUP_SNAPSHOT      ("CREATE CONSISTENCY GROUP SNAPSHOT",      "operation to create a consistency group snapshot"),
    DELETE_CONSISTENCY_GROUP_SNAPSHOT      ("DELETE CONSISTENCY GROUP SNAPSHOT",      "operation to delete a consistency group snapshot"),
    DEACTIVATE_CONSISTENCY_GROUP_SNAPSHOT  ("DEACTIVATE CONSISTENCY GROUP SNAPSHOT",  "operation to deactivate a consistency group snapshot"),
    ACTIVATE_CONSISTENCY_GROUP_SNAPSHOT    ("ACTIVATE CONSISTENCY GROUP SNAPSHOT",    "operation to activate a consistency group snapshot"),
    RESTORE_CONSISTENCY_GROUP_SNAPSHOT     ("RESTORE CONSISTENCY GROUP SNAPSHOT",     "operation to restore a consistency group snapshot"),
    CREATE_STORAGE_VIEW             ("CREATE STORAGE VIEW",                   "operation to create a storage view"),
    DELETE_STORAGE_VIEW             ("DELETE STORAGE VIEW",                   "operation to delete a storage view"),
    ADD_EXPORT_INITIATOR            ("ADD INITIATOR TO EXPORT GROUP",         "operation to add initiator to export group"),
    DELETE_EXPORT_INITIATOR         ("DELETE INITIATOR FROM EXPORT GROUP",    "delete initiator from export group."),
    ADD_STORAGE_VIEW_INITIATOR      ("ADD INITIATOR TO STORAGE VIEW",         "operation to add initiator to storage view"),
    DELETE_STORAGE_VIEW_INITIATOR   ("DELETE INITIATOR TO STORAGE VIEW",      "operation to delete initiator from storage view"),
    ROLLBACK_NOOP              ("ROLLBACK NOOP",            "no-op rollback operation"),
    PAUSE_NATIVE_CONTINUOUS_COPIES     ("PAUSE NATIVE CONTINUOUS COPIES", "Operation to pause Native Continuous Copies"),
    RESUME_NATIVE_CONTINUOUS_COPIES    ("RESUME NATIVE CONTINUOUS COPIES", "Operation to resume Native Continuous Copies"),
    CHECK_SYNC_PROGRESS    ("CHECK SYNC PROGRESS", "Operation to check progress between two block objects");

    private final String name;
    private final String description;

    ResourceOperationTypeEnum(String name, String description) {
        this.description = description;
        this.name = name;
    }

    /**
     * The name of the resource operation
     * @valid none
     */
    @XmlElement
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String toStr() {
        return name;
    }

    public static ResourceOperationTypeEnum fromString(String name) {
        return resourceOpMap.get(name);
    }

    private static final Map<String, ResourceOperationTypeEnum> resourceOpMap = new HashMap<String, ResourceOperationTypeEnum>();

    static {
        for (ResourceOperationTypeEnum res : values()) {
            resourceOpMap.put(res.name, res);
        }
    }
}
