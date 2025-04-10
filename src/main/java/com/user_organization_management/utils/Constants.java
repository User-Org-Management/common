package com.user_organization_management.utils;

public class Constants {
    // Organization messages and logs
    public static final String ORG_NOT_FOUND = "Organization not found";
    public static final String ORG_NAME_EXISTS = "Organization name already exists";

    public static final String LOG_FETCH_ALL_ORGS = "Fetching all organizations";
    public static final String LOG_FETCH_ORG_BY_ID = "Fetching organization by ID: { %s }";
    public static final String LOG_CREATE_ORG = "Creating organization with name: { %s }";
    public static final String LOG_ORG_CREATED = "Organization created with ID: { %s }";
    public static final String LOG_UPDATE_ORG = "Updating organization with ID: { %s }";
    public static final String LOG_ORG_UPDATED = "Organization updated with ID: { %s }";
    public static final String LOG_DELETE_ORG = "Deleting organization with ID: { %s }";
    public static final String LOG_DELETE_ORG_USERS = "Deleting { %s } users assigned to organization ID: { %s }";
    public static final String LOG_ORG_DELETED = "Organization with ID { %s } deleted";
    public static final String LOG_ORG_NAME_EXISTS = "Organization name '{ %s }' already exists.";


    // User creation logs
    public static final String USER_CREATION = "============== >> create a new user with email: { %s }";
    public static final String USER_CREATED = "============== >> User { %s } has been successfully created with ID: { %s }";
    public static final String USER_ASSIGNED_TO_ORG = "============== >> User { %s } is being assigned to organization with ID: { %s }";
    public static final String USER_NOT_ASSIGNED_TO_ORG = "============== >> User { %s } is not assigned to any organization.";
    public static final String PASSWORD_ENCODED = "============== >> Password for user { %s } has been encoded successfully.";
    public static final String USER_NAME_EXISTS = "Name { %s } already exists!.";


    // User update logs
    public static final String USER_UPDATING = "============== >> Updating user with ID: { %s }";
    public static final String USER_UPDATED = "============== >> User { %s } has been successfully updated.";
    public static final String USER_ASSIGNED_TO_ORG_UPDATE = "============== >> User { %s } assigned to organization ID: { %s }";
    public static final String USER_UNASSIGNED_FROM_ORG_UPDATE = "============== >> User { %s } unassigned from organization.";
    public static final String PASSWORD_UPDATED = "============== >> Password for user { %s } has been updated and encoded.";

    // User assign/unassign logs
    public static final String USER_ASSIGNING_ORG = "============== >> Assigning user with ID { %s } to organization with ID { %s }";
    public static final String USER_UNASSIGNED = "un assign user: { %s }";
    public static final String USER_UNASSIGNED_LOG = "user after un assign : { %s }";

    // User delete logs
    public static final String USER_DELETION = "check User with id :{ %s }";
    public static final String USER_DELETED = "user  deleted: { %s }";

    // Email check logs
    public static final String EMAIL_EXISTS = "Email { %s } already exists.";
    public static final String EMAIL_NOT_FOUND = "User with email { %s } not found";
    public static final String USER_NOT_FOUND = "User with id { %s } not found";

}
