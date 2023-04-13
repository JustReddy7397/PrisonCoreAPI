package ranked.network.prisoncore.api;

public class PrisonAPI {

    private static CoreAPI api;

    public static CoreAPI getApi() {
        if (api == null) throw new IllegalStateException("PrisonCore plugin not initiated.");
        return api;
    }

    public static void setApi(CoreAPI api) {
        PrisonAPI.api = api;
    }
}
