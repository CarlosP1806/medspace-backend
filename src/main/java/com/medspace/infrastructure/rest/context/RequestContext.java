package com.medspace.infrastructure.rest.context;

import com.medspace.domain.model.User;
import jakarta.enterprise.context.RequestScoped;
import lombok.Getter;
import lombok.Setter;

@RequestScoped
@Getter
@Setter
public class RequestContext {

    private User user;

    private String userId;

}
