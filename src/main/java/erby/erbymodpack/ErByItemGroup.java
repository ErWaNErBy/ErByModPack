package erby.erbymodpack;

import erby.erbymodpack.lists.BlockList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ErByItemGroup extends ItemGroup {

    public ErByItemGroup(){
        super("erbyitemgroup"); //name of the item group
    }

    public ItemStack createIcon()
    {
        return new ItemStack(Item.BLOCK_TO_ITEM.get(BlockList.black_stone));    //icon of the Item group
    }


}
