package ranked.network.prisoncore.api.mine;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import ranked.network.prisoncore.api.user.PrisonUser;
import ranked.network.prisoncore.api.util.Cuboid;

import java.util.Map;

public interface Mine {

    String getId();

    String getDisplayName();

    void setDisplayName(String displayName);

    MineArea getArea();

    ItemStack getIcon();

    void setIcon(Material icon);

    int getResetTime();

    void setResetTime(int resetTime);

    String getPermission();

    void setPermission(String permission);

    Location getSpawnLocation();

    void setSpawnLocation(Location location);

    Map<Material, Integer> getBlockChance();

    Map<Material, Double> getSellPrice();

    void addBlockChance(Material material, int chance);

    void addBlockPrice(Material material, double price);

    void removeBlock(Material material);

    boolean isResetting();

    void reset();

    void startTask();

    void stopTask();

    void teleportToSpawn(PrisonUser user);




}
