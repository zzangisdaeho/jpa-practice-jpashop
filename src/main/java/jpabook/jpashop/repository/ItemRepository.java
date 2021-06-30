package jpabook.jpashop.repository;

import jpabook.jpashop.entity.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item){
        if(item.getId() == null){
            em.persist(item);
        } else {
            //merge : update와 비슷한거
            // update가 나가기 전에 식별자를 통해 find한다. (있는지 확인해보기 위해서)
            // select결과가 있으면 update, select결과가 없다면 insert문이 나간다. 고로 merge는 select가 한번 더 무조건 나가기 때문에 비효율적
            //merge는 쉽게말해 parameter에서 id를 통해 db에서 entity를 가져온 후 식별자를 제외한 모든 col을 paramter로 바꿔치기하여 업데이트하는것
            //merge로 return되는 객체는 영속성 컨텍스트 안에 있다.(컨텍스트에 들어가서 업데이트되고 나온거니까)
            //merge는 모든 필드를 바꿔치기 하기 때문에 paramter에 값을 주지 않으면(null) 해당 필드는 다 null로 업데이트된다. 고로 일부분만 바꾸고 싶으면 로딩해와서 해당부분만 바꿔줘야할것
            //결론 : 나중에 데이터 null로 날아가는 대형사고 터지기 싫으면 왠만하면 merge는 지양해라
            em.merge(item);
        }
    }

    public Item findOne(Long id){
        return em.find(Item.class, id);
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
