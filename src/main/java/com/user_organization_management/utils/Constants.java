package com.user_organization_management.utils;

public class Constants {
    // Organization messages and logs
    public static final String ORG_NOT_FOUND = "Organization not found";
    public static final String ORG_NAME_EXISTS = "Organization name already exists";

    public static final String LOG_FETCH_ALL_ORGS = "Fetching all organizations";
    public static final String LOG_FETCH_ORG_BY_ID = "Fetching organization by ID: {}";
    public static final String LOG_CREATE_ORG = "Creating organization with name: {}";
    public static final String LOG_ORG_CREATED = "Organization created with ID: {}";
    public static final String LOG_UPDATE_ORG = "Updating organization with ID: {}";
    public static final String LOG_ORG_UPDATED = "Organization updated with ID: {}";
    public static final String LOG_DELETE_ORG = "Deleting organization with ID: {}";
    public static final String LOG_DELETE_ORG_USERS = "Deleting {} users assigned to organization ID: {}";
    public static final String LOG_ORG_DELETED = "Organization with ID {} deleted";
    public static final String LOG_ORG_NAME_EXISTS = "Organization name '{}' already exists.";


    // User creation logs
    public static final String USER_CREATION = "============== >> create a new user with email: {}";
    public static final String USER_CREATED = "============== >> User {} has been successfully created with ID: {}";
    public static final String USER_ASSIGNED_TO_ORG = "============== >> User {} is being assigned to organization with ID: {}";
    public static final String USER_NOT_ASSIGNED_TO_ORG = "============== >> User {} is not assigned to any organization.";
    public static final String PASSWORD_ENCODED = "============== >> Password for user {} has been encoded successfully.";
    public static final String USER_NAME_EXISTS = "Name {} already exists!.";


    // User update logs
    public static final String USER_UPDATING = "============== >> Updating user with ID: {}";
    public static final String USER_UPDATED = "============== >> User {} has been successfully updated.";
    public static final String USER_ASSIGNED_TO_ORG_UPDATE = "============== >> User {} assigned to organization ID: {}";
    public static final String USER_UNASSIGNED_FROM_ORG_UPDATE = "============== >> User {} unassigned from organization.";
    public static final String PASSWORD_UPDATED = "============== >> Password for user {} has been updated and encoded.";

    // User assign/unassign logs
    public static final String USER_ASSIGNING_ORG = "============== >> Assigning user with ID {} to organization with ID {}";
    public static final String USER_UNASSIGNED = "un assign user: {}";
    public static final String USER_UNASSIGNED_LOG = "user after un assign : {}";

    // User delete logs
    public static final String USER_DELETION = "check User with id :{}";
    public static final String USER_DELETED = "user  deleted: {}";

    // Email check logs
    public static final String EMAIL_EXISTS = "Email {} already exists.";
    public static final String EMAIL_NOT_FOUND = "User with email {} not found %s";
    public static final String USER_NOT_FOUND = "User with id {} not found";

}
