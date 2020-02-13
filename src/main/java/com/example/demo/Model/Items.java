package com.example.demo.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@SqlResultSetMapping(
        name="ItemsResult",
        entities={
                @EntityResult(
                        entityClass = com.example.demo.Model.Items.class,
                        fields={@FieldResult(name="itemId",column="item_Id"),
                                 @FieldResult(name="itemName", column="item_name"),
                                 @FieldResult(name="qty", column="qty"),
                                 @FieldResult(name="inv", column="inv")}
                )
        }
)

@NamedNativeQuery(name = "findAllByInvId", query = "select * from items i where i.qty=:invId",resultClass=Items.class)
@Table(name="items")
public class Items {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long itemId;
	@NotNull
    @Size(max = 50)
	private String itemName;
	@NotNull
    
	int qty;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "inv_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
	private Inventory inventory;
	
	public Inventory getInv() {
		return inventory;
	}

	public void setInv(Inventory inv) {
		this.inventory = inv;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String name) {
		this.itemName = name;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public Items() {
		// TODO Auto-generated constructor stub
	}
	public Items(String name,Integer qty,Inventory inventory) {
		this.itemName = name;
		this.qty = qty;
		this.inventory = inventory;
	}
	@Override
	public String toString() {
		return "Items [itemId=" + itemId + ", itemName=" + itemName + ", qty=" + qty + ", inv=" + inventory + "]";
	}
}
