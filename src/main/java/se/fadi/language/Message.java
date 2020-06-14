package se.fadi.language;

public enum Message {
    PORTAL_CREATION_SUCCESS("AVCreate.Portal-Creation.Success"),
    PORTAL_CREATION_FAILURE("AVCreate.Portal-Creation.Failure"),
    PORTAL_CREATION_EXISTING_PORTAL("AVCreate.Portal-Creation.Existing-Portal"),
    PORTAL_CREATION_INVALID_ARGUMENTS("AVCreate.Portal-Creation.Invalid-Arguments"),
    PORTAL_DELETION_SUCCESS("AVDelete.Portal-Deletion.Success"),
    PORTAL_DELETION_FAILURE("AVDelete.Portal-Deletion.Failure"),
    PORTAL_DELETION_INVALID_ARGUMENTS("AVDelete.Portal-Deletion.Invalid-Arguments"),
    PORTAL_LINKING_SUCCESS("AVLink.Portal-Linking.Success"),
    PORTAL_LINKING_FAILURE("AVLink.Portal-Linking.Failure"),
    PORTAL_LINKING_INVALID_ARGUMENTS("AVLink.Portal-Linking.Invalid-Arguments"),
    AVTELEPORT_SELF_SUCCESS("AVTeleport.Self.Success"),
    AVTELEPORT_SELF_FAILURE("AVTeleport.Self.Failure"),
    AVTELEPORT_PLAYER_SUCCESS("AVTeleport.Player.Success"),
    AVTELEPORT_PLAYER_FAILURE("AVTeleport.Player.Failure"),
    AVTELEPORT_INVALID_ARGUMENTS("AVTeleport.Invalid-Arguments"),
    PREFIX("Prefix");

    private String messagePath;

    Message(String messagePath) {
        this.messagePath = messagePath;
    }

    public String getMessagePath() {
        return messagePath;
    }
}
