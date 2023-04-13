package ranked.network.prisoncore.api;

import com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2RTFDTM;
import ranked.network.prisoncore.api.mine.Mine;
import ranked.network.prisoncore.api.user.PrisonUser;

import java.util.UUID;

public interface CoreAPI {

    PrisonUser getPrisonUser(UUID uuid);

    @Deprecated
    PrisonUser getPrisonUser(String name);

    Mine getMine(String id);

}
