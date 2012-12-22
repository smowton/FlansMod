package co.uk.flansmods.client;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import co.uk.flansmods.common.ContainerPlaneMenu;
import co.uk.flansmods.common.EntityDriveable;
import co.uk.flansmods.common.EntityPlane;
import co.uk.flansmods.common.FlansMod;
import co.uk.flansmods.common.network.PacketVehicleGUI;
import cpw.mods.fml.common.network.PacketDispatcher;


public class GuiPlaneMenu extends GuiContainer
{
    public GuiPlaneMenu(InventoryPlayer inventoryplayer, World world1, EntityDriveable entPlane)
    {
        super(new ContainerPlaneMenu(inventoryplayer, world1));
		entity = entPlane;
		ySize = 180;
		world = world1;
		inventory = inventoryplayer;
    }
	
	public void initGui()
	{
		super.initGui();
		controlList.add(new GuiButton(0, width / 2 - 60, height / 2 - 71, 58, 20, "Guns"));
		controlList.add(new GuiButton(1, width / 2 + 2, height / 2 - 71, 58, 20, (entity instanceof EntityPlane ? "Bombs" : "Shells")));
		controlList.add(new GuiButton(2, width / 2 - 60, height / 2 - 49, 58, 20, "Fuel"));
		controlList.add(new GuiButton(3, width / 2 + 2, height / 2 - 49, 58, 20, "Cargo"));
	}
	
	protected void actionPerformed(GuiButton button)
    {
		//Replace with a packet requesting the GUI from the server
        if (button.id == 0) //Guns
        {
        	PacketDispatcher.sendPacketToServer(PacketVehicleGUI.buildGUIPacket(0));
        	inventory.player.openGui(FlansMod.instance, 6, world, entity.chunkCoordX, entity.chunkCoordY, entity.chunkCoordZ);
        }
        if (button.id == 1) //Bombs
        {
        	PacketDispatcher.sendPacketToServer(PacketVehicleGUI.buildGUIPacket(1));
        	inventory.player.openGui(FlansMod.instance, 7, world, entity.chunkCoordX, entity.chunkCoordY, entity.chunkCoordZ);
        }
        if (button.id == 2) //Fuel
        {
        	PacketDispatcher.sendPacketToServer(PacketVehicleGUI.buildGUIPacket(2));
        	inventory.player.openGui(FlansMod.instance, 8, world, entity.chunkCoordX, entity.chunkCoordY, entity.chunkCoordZ);
        } 
		if (button.id == 3) //Cargo
        {
			PacketDispatcher.sendPacketToServer(PacketVehicleGUI.buildGUIPacket(3));
			inventory.player.openGui(FlansMod.instance, 9, world, entity.chunkCoordX, entity.chunkCoordY, entity.chunkCoordZ);
        }
    }

    protected void drawGuiContainerForegroundLayer(int i, int j)
    {
        fontRenderer.drawString(entity.getSuperType().name, 6, 6, 0x404040);
        fontRenderer.drawString("Inventory", 8, (ySize - 96) + 2, 0x404040);
    }

    protected void drawGuiContainerBackgroundLayer(float f, int i1, int j1)
    {
        int i = mc.renderEngine.getTexture("/gui/planeMenu.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(i);
        int j = (width - xSize) / 2;
        int k = (height - ySize) / 2;
        drawTexturedModalRect(j, k, 0, 0, xSize, ySize);
    }

	public World world;
	public InventoryPlayer inventory;
	public EntityDriveable entity;
}
