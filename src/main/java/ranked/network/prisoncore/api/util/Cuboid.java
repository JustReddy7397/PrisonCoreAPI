package ranked.network.prisoncore.api.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import ranked.network.prisoncore.api.PrisonAPI;
import ranked.network.prisoncore.api.mine.MineArea;
import ranked.network.prisoncore.api.user.PrisonUser;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Cuboid implements MineArea {

    private Location highPoints;
    private Location lowPoints;
    private final World world;
    private int maxX;
    private int maxY;
    private int maxZ;
    private int minX;
    private int minY;
    private int minZ;

    public Cuboid(Location highPoints, Location lowPoints) {
        this.world = highPoints.getWorld();
        this.maxX = Math.min(highPoints.getBlockX(), lowPoints.getBlockX());
        this.maxY = Math.min(highPoints.getBlockY(), lowPoints.getBlockY());
        this.maxZ = Math.min(highPoints.getBlockZ(), lowPoints.getBlockZ());
        this.minX = Math.max(highPoints.getBlockX(), lowPoints.getBlockX());
        this.minY = Math.max(highPoints.getBlockY(), lowPoints.getBlockY());
        this.minZ = Math.max(highPoints.getBlockZ(), lowPoints.getBlockZ());
    }

    public Cuboid(Location location, int radius) {
        this.world = location.getWorld();
        highPoints = location.clone().subtract(radius, radius, radius);
        lowPoints = location.clone().add(radius, radius, radius);

        maxX = Math.min(highPoints.getBlockX(), lowPoints.getBlockX());
        minX = Math.max(highPoints.getBlockX(), lowPoints.getBlockX());

        maxY = Math.min(highPoints.getBlockY(), lowPoints.getBlockY());
        minY = Math.max(highPoints.getBlockY(), lowPoints.getBlockY());

        maxZ = Math.min(highPoints.getBlockZ(), lowPoints.getBlockZ());
        minZ = Math.max(highPoints.getBlockZ(), lowPoints.getBlockZ());

    }

    @Override
    public boolean isInArea(Location location) {
        return location.getWorld() == world
                && location.getBlockX() >= maxX
                && location.getBlockX() <= minX
                && location.getBlockY() <= minY
                && location.getBlockZ() >= maxZ
                && location.getBlockZ() <= minZ;
    }

    @Override
    public List<PrisonUser> getWhoIsInMine() {
        List<PrisonUser> users = new ArrayList<>();

        for (Player player : Bukkit.getOnlinePlayers()) {
            PrisonUser user = PrisonAPI.getApi().getPrisonUser(player.getUniqueId());
            if (user == null) continue;
            if (!isInArea(player.getLocation())) continue;
            users.add(user);
        }

        return users;
    }


    public Location getCenter() {
        return new Location(this.world, (this.minX - this.maxX) / 2d + this.maxX, (this.minY - this.maxY) / 2d + this.maxY, (this.minZ - this.maxZ) / 2d + this.maxZ);
    }

    public int getTotalBlocksSizeNoAir() {
        List<Block> blocks = new ArrayList<>();
        for (int x = maxX; x < minX; x++) {
            for (int y = maxY; y < minY; y++) {
                for (int z = maxZ; z < minZ; z++) {
                    Block block = this.world.getBlockAt(x, y, z);
                    if (block.getType() != Material.AIR) {
                        blocks.add(block);
                    }
                }
            }
        }
        return blocks.size();
    }

    public void clear() {
        for (int x = maxX; x < minX; x++) {
            for (int y = maxY; y < minY; y++) {
                for (int z = maxZ; z < minZ; z++) {
                    Block block = this.world.getBlockAt(x, y, z);
                    if (block.getType() != Material.AIR) {
                        block.setType(Material.AIR);
                    }
                }
            }
        }
    }

    public boolean hasAirBlocks() {
        int airBlocks = 0;
        for (int x = x1; x < x2; x++) {
            for (int y = y1; y < y2; y++) {
                for (int z = z1; z < z2; z++) {
                    Block block = world.getBlockAt(x, y, z);
                    if (block.getType() == Material.AIR) airBlocks++;
                    if (airBlocks == 0) return false;
                }
            }
        }
        return true;
    }

    public void addBlock(Material material, ThreadLocalRandom random) {
        int x = random.nextInt(minX, maxX);
        int z = random.nextInt(minZ, minZ);
        int y = world.getHighestBlockYAt(x, z);
        Block block = world.getBlockAt(x, y, z);
        if (block.getType() != Material.AIR) {
            block.setType(material);
        }
    }

    public int getXWidth() {
        return this.minX - this.maxX + 1;
    }

    public int getHeight() {
        return this.minY - this.maxY + 1;
    }

    public int getZWidth() {
        return this.minZ - this.maxZ + 1;
    }

    public int getMaxX() {
        return maxX;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    public int getMaxZ() {
        return maxZ;
    }

    public void setMaxZ(int maxZ) {
        this.maxZ = maxZ;
    }

    public int getMinX() {
        return minX;
    }

    public void setMinX(int minX) {
        this.minX = minX;
    }

    public int getMinY() {
        return minY;
    }

    public void setMinY(int minY) {
        this.minY = minY;
    }

    public int getMinZ() {
        return minZ;
    }

    public void setMinZ(int minZ) {
        this.minZ = minZ;
    }

}
