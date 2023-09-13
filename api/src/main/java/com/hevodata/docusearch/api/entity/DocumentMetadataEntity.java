package com.hevodata.docusearch.api.entity;

import com.hevodata.docusearch.model.SourceEnum;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "documents")
public class DocumentMetadataEntity {

    @Id
    private String id;

    @Field(store = true, type = FieldType.Text, fielddata = true)
    private String title;

    @Field(store = true, type = FieldType.Text, fielddata = true)
    private String content;

    @Field(store = true, type = FieldType.Text, fielddata = true)
    private String downloadPath;

    @Field(store = true, fielddata = true, type = FieldType.Text)
    private SourceEnum source;
}
