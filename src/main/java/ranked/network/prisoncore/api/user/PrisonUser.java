package ranked.network.prisoncore.api.user;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.UUID;

public interface PrisonUser {

    UUID getUniqueId();

    String getName();

    Player getPlayer();

    Location getLocation();

    void sendMessage(String message);

    void sendTitle(String title, String subTitle);

    void sendSound(Sound sound);

    void teleport(Location location);

}
