package com.marius.config;

import com.marius.model.domain.user.CustomUserDetails;
import org.bson.Document;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DatabaseBeforeSaveListener extends AbstractMongoEventListener<Object> {

    @Override
    public void onBeforeSave(BeforeSaveEvent<Object> event) {

        Document eventObject = event.getDocument();

        if (null != eventObject) {
            putCreatedDate(eventObject);
            putCreatorOrUpdater(eventObject);
        }
        super.onBeforeSave(event);
    }

    private void putCreatorOrUpdater(Document eventObject) {
            eventObject.putIfAbsent("createdBy", getUserName(SecurityContextHolder.getContext().getAuthentication()));
            eventObject.putIfAbsent("changedBy", getUserName(SecurityContextHolder.getContext().getAuthentication()));
    }

    // this must be here in case you are creating a user, cause no logged in user is required for that
    private String getUserName(Authentication authentication) {
        return authentication.getPrincipal() instanceof String ? (String) authentication.getPrincipal()
                : ((CustomUserDetails) authentication.getPrincipal()).getUser().getUserName();
    }
    private void putCreatedDate(Document eventObject) {
            eventObject.putIfAbsent("createdOn", LocalDateTime.now());
            eventObject.putIfAbsent("changedOn", LocalDateTime.now());
    }
}
