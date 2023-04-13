package ranked.network.prisoncore.api.mine;

import org.bukkit.Location;
import ranked.network.prisoncore.api.user.PrisonUser;

import java.util.List;

public interface MineArea {

    boolean isInArea(Location location);

    List<PrisonUser> getWhoIsInMine();

}
