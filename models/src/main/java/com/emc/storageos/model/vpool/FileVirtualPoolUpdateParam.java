package com.emc.storageos.model.vpool;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Parameter to File VirtualPool update.
 */
@XmlRootElement(name = "file_vpool_update")
public class FileVirtualPoolUpdateParam extends VirtualPoolUpdateParam {
    
    private FileVirtualPoolProtectionParam protection;

    public FileVirtualPoolUpdateParam() {}
    
    public FileVirtualPoolUpdateParam(FileVirtualPoolProtectionParam protection) {
        super();
        this.protection = protection;
    }

    /**
     * The new protection settings for the virtual pool.
     * 
     * @valid none
     */
    @XmlElement(name = "protection")
    public FileVirtualPoolProtectionParam getProtection() {
        return protection;
    }

    public void setProtection(FileVirtualPoolProtectionParam protection) {
        this.protection = protection;
    }
    
}
