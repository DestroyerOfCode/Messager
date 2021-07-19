package com.marius.config;

import com.marius.model.domain.user.CustomUserDetails;
import org.bson.Document;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class DatabaseBeforeSaveListener extends AbstractMongoEventListener<Object> {

    @Override
    public void onBeforeSave(BeforeSaveEvent<Object> event) {

        Document eventObject = event.getDocument();

        if (null != eventObject) {
            putCreatedDate(eventObject);

            Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                    .ifPresent((auth) -> Optional.ofNullable(auth.getPrincipal())
                        .ifPresent(p -> putCreatorOrUpdater(eventObject))
                    );
        }
        super.onBeforeSave(event);
    }

    private void putCreatorOrUpdater(Document eventObject) {
        if (eventObject.get("createdBy") == null)
            eventObject.put("createdBy", ((CustomUserDetails) SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getPrincipal())
                    .getUser()
                    .getUserName()
            );
        else
            eventObject.put("changedBy", ((CustomUserDetails) SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getPrincipal())
                    .getUser()
                    .getUserName()
            );
    }

    private void putCreatedDate(Document eventObject) {
        if (eventObject.get("createdOn") == null)
            eventObject.put("createdOn", LocalDateTime.now());
        else
            eventObject.put("changedOn", LocalDateTime.now());
    }
}
