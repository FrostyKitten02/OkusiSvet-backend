package si.feri.okusisvet.enums;

public enum RecipeState {
    PRIVATE_DRAFT(false),
    PUBLIC_PUBLISHED(true),
    PRIVATE_COPY(false);
    private final boolean showInPublicList;

    RecipeState(boolean showInPublicList) {
        this.showInPublicList = showInPublicList;
    }
}
