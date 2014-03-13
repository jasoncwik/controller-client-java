/**
 *  Copyright (c) 2008-2013 EMC Corporation
 * All Rights Reserved
 *
 * This software contains the intellectual property of EMC Corporation
 * or is licensed to EMC Corporation from third parties.  Use of this
 * software and the intellectual property contained therein is expressly
 * limited to the terms and conditions of the License Agreement under which
 * it is provided by or on behalf of EMC.
 */
package com.emc.storageos.model.vpool;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "mirror")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class VirtualPoolProtectionMirrorRestRep {
    private String type;
    private String placementVpool;

    public VirtualPoolProtectionMirrorRestRep() {}
    
    public VirtualPoolProtectionMirrorRestRep(String type, String placementVpool) {
        this.type = type;
        this.placementVpool = placementVpool;
    }

    /**
     * The mirror protection type.
     * 
     * @valid none
     * 
     * @return The mirror protection type.
     */
    @XmlElement(name = "type")
    public String getType() {
        return type;
    }

    /**
     * The mirror protection virtual pool.
     * 
     * @valid none
     * 
     * @return The mirror protection virtual pool.
     */
    @XmlElement(name = "protection_mirror_vpool")
    public String getPlacementVpool() {
        return placementVpool;
    }

    public void setPlacementVpool(String placementVpool) {
        this.placementVpool = placementVpool;
    }

    public void setType(String type) {
        this.type = type;
    }
}