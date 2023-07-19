package org.ssce.datasets.model;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@NoArgsConstructor
@TypeDefs({@TypeDef(name = "jsonb",typeClass = JsonBinaryType.class)})
public class Dataset {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    @Pattern(regexp = "[a-zA-Z]*", message = "name should be in alphabets")
    @NotEmpty(message = "name should not be empty")
    @Column(name = "name")
    private String name;

    @NotNull(message = "data must be entered")
    @Type(type = "jsonb")
    @Column(name = "data_schema" ,columnDefinition = "jsonb")
    private Map<String , Object> dataSchema ;

    @NotNull(message = "data must be entered")
    @Type(type = "jsonb")
    @Column(name = "router_config", columnDefinition = "jsonb")
    private  Map<String , Object> routerConfig;

    @Enumerated(EnumType.STRING)
    private Status status;

    @NotEmpty(message = "enter created by name")
    @NotBlank
    @Column(name = "created_by")
    private String createdBy;

    @NotEmpty(message = "enter updated by name")
    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    public enum Status{
        LIVE,
        DRAFT,
        RETIRED;

    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getDataSchema() {
        return dataSchema;
    }

    public void setDataSchema(Map<String, Object> dataSchema) {
        this.dataSchema = dataSchema;
    }

    public Map<String, Object> getRouterConfig() {
        return routerConfig;
    }

    public void setRouterConfig(Map<String, Object> routerConfig) {
        this.routerConfig = routerConfig;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }
}
