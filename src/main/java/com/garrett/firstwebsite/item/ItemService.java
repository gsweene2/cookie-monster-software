package com.garrett.firstwebsite.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.expression.Numbers;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class ItemService {

    /**
     * Dependency Injection of Item Repository, singleton.
     * Note that when the framework sees apache Derby database in the classpath,
     * it uses that database. No further connection needed!
     */

    @Autowired
    private ItemRepository itemRepository;

    private List<Item> items = new ArrayList<>();

    public List<Item> getAllItem() {
        //return items
        //
        List<Item> items = new ArrayList<>();
        // Lambda
        itemRepository.findAll().forEach(items::add);
        // return the list
        return items;
    }

    public List<PrettyItem> getAllPrettyItem(){
        //return items
        //
        List<Item> items = new ArrayList<>();
        // Lambda
        itemRepository.findAll().forEach(items::add);
        List<PrettyItem> prettyItems = new ArrayList<>();
        for (Item item : items){
            prettyItems.add(itemToPrettyItem(item));
        }
        // return the list
        return prettyItems;
    }

    private PrettyItem itemToPrettyItem(Item item){
        Numbers numbers = new Numbers(new Locale("en","US"));
        return new PrettyItem(
                item.getId(),
                item.getName(),
                numbers.formatCurrency(item.getAmount()),
                item.getInstock()
        );
    }

    public Optional<Item> getItem(long id) {
        //return transactions
        return itemRepository.findById(id);
    }

    public void addItem(Item item){
        itemRepository.save(item);
    }

    public void updateItem(long id, Item item){
        itemRepository.save(item);
    }

    public void deleteItem(long id) { itemRepository.deleteById(id);}
}
