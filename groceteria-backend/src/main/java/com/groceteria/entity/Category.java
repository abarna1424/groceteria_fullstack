package com.groceteria.entity;

import java.util.HashMap;
import java.util.Map;



public enum Category {
	
	   VEGETABLES(0),
	   FRUITS(1),
	   DAIRYPRODUCTS(2),
	   MEAT(3),
	   GRAINSANDOILS(4),
	   SPICESANDSEASONINGS(5),
	   BAKINGINGREDIENTS(6),
	   CONDIMENTS(7),
	   SNACKS(8),
	   SKINCARE(9);
	
	private int value;
    private static Map map = new HashMap<>();

    private Category(int value) {
        this.value = value;
    }

    static {
        for (Category category : Category.values()) {
            map.put(category.value, category);
        }
    }

    public static Category valueOf(int category) {
        return (Category) map.get(category);
    }

    public int getValue() {
        return value;
    }
	

}
