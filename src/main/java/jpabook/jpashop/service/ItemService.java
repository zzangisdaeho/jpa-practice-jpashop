package jpabook.jpashop.service;

import jpabook.jpashop.entity.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    @Transactional
    public Item updateItem(Item item){
        //find하여 영속성컨텍스트에 넣었기 때문에 set만 해주어도 변경감지(dirty-checking)이 일어나서 update된다
        Item getItem = itemRepository.findOne(item.getId());
        getItem.setStockQuantity(item.getStockQuantity());
        getItem.setPrice(item.getPrice());
        getItem.setName(item.getName());
        getItem.setCategories(item.getCategories());

        return getItem;
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }
}
