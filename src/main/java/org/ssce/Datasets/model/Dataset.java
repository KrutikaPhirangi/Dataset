package org.ssce.Datasets.model;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
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
    private Map<String , Object> dataSchema = new HashMap<>();

    @NotNull(message = "data must be entered")
    @Type(type = "jsonb")
    @Column(name = "router_config", columnDefinition = "jsonb")
    private  Map<String , Object> routerConfig;

    @Enumerated(EnumType.STRING)
    private Status status;

    @NotEmpty(message = "enter created by name")
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
}
