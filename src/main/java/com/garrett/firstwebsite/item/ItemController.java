package com.garrett.firstwebsite.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.expression.Numbers;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Controller
@RequestMapping("/item")
public class ItemController {

    /**
     * Dependency Injection of UserService, singleton.
     */
    @Autowired
    ItemService itemService;

    /**
     * Get all transactions
     */
    @RequestMapping("")
    public String getAllTransactions(Model model) {
        List<PrettyItem> items = itemService.getAllPrettyItem();
        return "item/allItems";
    }

    @RequestMapping("/create")
    public String createItemForm(Model model){
        model.addAttribute("itemPassed", new Item());
        return "item/create";
    }

    @PostMapping("/create")
    public String createItemResult(@ModelAttribute Item item){
        itemService.addItem(item);
        return "item/result";
    }

    /**
     * Get Item
     *
     */
    @RequestMapping("/{id}")
    public Optional<Item> getTransaction(@PathVariable("id") long id) {
        return  itemService.getItem(id);
    }

    /**
     * Add Item
     */
    @RequestMapping(method= RequestMethod.POST, value="")
    public void addTransaction(@RequestBody Item item) {
        itemService.addItem(item);
    }

    /**
     * Update Item
     */
    @RequestMapping(method=RequestMethod.PUT, value="/{id}")
    public void updateTransaction(@RequestBody Item item, @PathVariable long id){
        itemService.updateItem(id, item);
    }

    /**
     * Delete Item
     */
    @RequestMapping(method=RequestMethod.DELETE,value="")
    public void deleteTransaction(@PathVariable long id) {
        itemService.deleteItem(id);
    }

}
