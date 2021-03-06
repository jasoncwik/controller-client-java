package com.emc.vipr.model.object.bucket;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.lang.Boolean;
import java.lang.String;
import java.net.URI;

/**
 * Object bucket creation parameters.
 */
@XmlRootElement(name = "object_bucket_create")
public class ObjectBucketParam {

    private String name;
    private URI project;
    private URI vpool;
    private Boolean filesystemEnabled;

    public ObjectBucketParam() { }

    /**
     * user provided bucket name
     */
    @XmlElement(required = true, name = "name")
    public String getName() {
        return name;
    }

    /**
     * user provided bucket project id
     */
    @XmlElement(required = true, name = "project")
    public URI getProject() {
        return project;
    }

    /**
     * user provided bucket vpool id
     */
    @XmlElement(required = true, name = "vpool")
    public URI getVpool() {
        return vpool;
    }

    /**
     * flag indicating whether file-system is enabled for this bucket
     */
    @XmlElement(required = true, name = "filesystem_enabled")
    public Boolean getFilesystemEnabled() {
        return filesystemEnabled;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProject(URI project) {
        this.project = project;
    }

    public void setVpool(URI vpool) {
        this.vpool = vpool;
    }

    public void setFilesystemEnabled(Boolean filesystemEnabled) {
        this.filesystemEnabled = filesystemEnabled;
    }

}

