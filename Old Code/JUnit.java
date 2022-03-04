package abhi.ooad;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class JUnit {
    Store store;

    public JUnit() throws NoSuchFieldException {
        this.store = new Store();
    }

    @Test
    @DisplayName("Staff Initialization")
    void staffInitialization() {
        assertTrue(this.store.clerkRecord.size() == 3);
    }

    @Test
    @DisplayName("Inventory Initialization")
    void inventoryInitialization() {
        int invItemCount = 0;
        for(String str:this.store.inventory.keySet()){
            invItemCount += this.store.inventory.get(str).size();
        }
        assertTrue(this.store.inventory.size() == 21);
        assertTrue(invItemCount == this.store.inventory.size()*3);
    }

    @Test
    @DisplayName("Bank Visit")
    void goToBank() {
        float amount = this.store.registerValue;
        this.store.activeClerk = this.store.clerkRecord.get(0);
        this.store.goToBank();
        assertTrue(this.store.registerValue == amount + 1000);
    }

    @Test
    @DisplayName("Add item to inventory")
    void addItem() {
        ArrayList<String> items = new ArrayList<>();
        items.add("CD");
        items.add("Guitar");
        int itemCDSize = this.store.inventory.get("CD").size();
        int itemGuitarSize = this.store.inventory.get("Guitar").size();
        this.store.addItems(items, false, 1, "New", -1.00F, 5);
        assertTrue(this.store.inventory.get("CD").size() == itemCDSize+3);
        assertTrue(this.store.inventory.get("Guitar").size() == itemGuitarSize+3);
    }

    @Test
    @DisplayName("Active Clerk")
    void activeClerk() {
        assertTrue(this.store.activeClerk == null);
        this.store.arriveAtStore();
        assertTrue(this.store.activeClerk != null);
        assertTrue(this.store.activeClerk.getWorkingDayCount() == 1);
        assertTrue(this.store.activeClerk instanceof Staff);
    }

    @Test
    @DisplayName("Select item randomly from inventory")
    void selectItem() throws NoSuchFieldException {
        assertTrue(!this.store.inventory.isEmpty());
        Item selectedItem = this.store.selectItem();
        assertTrue(selectedItem != null);
        assertTrue(this.store.inventory.get(selectedItem.getName()).contains(selectedItem));
    }

    @Test
    @DisplayName("Condition Int/String Conversion")
    void checkConditionConv() {
        assertEquals(1, this.store.getConditionLevel("Poor"));
        assertEquals(5, this.store.getConditionLevel("Default"));
        assertEquals("Fair", this.store.getConditionText(2));
        assertEquals("Excellent", this.store.getConditionText(100));
    }

    @Test
    @DisplayName("Customer List")
    void customerList() {
        ArrayList<String> cust = new ArrayList<>();
        cust = this.store.getCustomers();
        assertTrue(!cust.isEmpty());
        assertTrue(cust.size()>=3 && cust.size()<=16);
    }

    @Test
    @DisplayName("Sell to Customer")
    void sellToCustomer() throws NoSuchFieldException {
        Map<String, List<Item>> inv = new HashMap<>();
        int invBefore = 0;
        int invAfter = 0;
        float cashRegister = this.store.registerValue;

        this.store.activeClerk = this.store.clerkRecord.get(0);
        assertTrue(this.store.soldItems.isEmpty());
        for (String str : this.store.inventory.keySet()) {
            invBefore += this.store.inventory.get(str).size();
        }
        this.store.processBuyingCustomer(1);
        for (String str : this.store.inventory.keySet()) {
            invAfter += this.store.inventory.get(str).size();
        }
        if (invAfter == invBefore - 1) {
            assertTrue(!this.store.soldItems.isEmpty());
            assertTrue(cashRegister + this.store.soldItems.get(this.store.soldItems.keySet().toArray()[0]).get(0).getSalePrice() == this.store.registerValue);
        }
    }

    @Test
    @DisplayName("Buy from Customer")
    void buyFromCustomer() throws NoSuchFieldException {
        Map<String, List<Item>> inv = new HashMap<>();
        int invBefore = 0;
        int invAfter = 0;
        float cashRegister = this.store.registerValue;

        this.store.activeClerk = this.store.clerkRecord.get(0);
        assertTrue(this.store.boughtItems.isEmpty());
        for(String str:this.store.inventory.keySet()) {
            invBefore += this.store.inventory.get(str).size();
        }
        this.store.processSellingCustomer(1);
        for(String str:this.store.inventory.keySet()) {
            invAfter += this.store.inventory.get(str).size();
        }
        if(invAfter == invBefore+1) {
            assertTrue(!this.store.boughtItems.isEmpty());
            assertTrue(cashRegister - this.store.boughtItems.get(this.store.boughtItems.keySet().toArray()[0]).get(0).getPurchasePrice() == this.store.registerValue);
        }
    }
}