package org.accountinglib;

import org.accountinglib.model.Customer;
import org.accountinglib.model.OrderLine;
import org.accountinglib.model.Orders;
import org.accountinglib.model.Product;
import org.accountinglib.repository.CustomerRepository;
import org.accountinglib.repository.OrderLineRepository;
import org.accountinglib.repository.OrdersRepository;
import org.accountinglib.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Initialize the database with test data. Note that this also deletes all old data on startup.
 */
@Component
public class DataInitializer implements ApplicationRunner {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderLineRepository orderLineRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {



        orderLineRepository.deleteAll();
        ordersRepository.deleteAll();
        customerRepository.deleteAll();
        productRepository.deleteAll();


        Customer customer1 = new Customer();
        customer1.setName("Ingvar Kamprad");
        customer1.setEmail("ingmar@kkea.com");
        customer1.setMemberCategory(Customer.MemberCategory.NOT_MEMBER);
        customerRepository.save(customer1);


        Product product_sofa = new Product();
        product_sofa.setName("Sofa");
        product_sofa.setNumber(1001);
        product_sofa.setWeight(120);
        product_sofa.setDescription("A high quality sofa.");
        product_sofa.setCategory(Product.ProductCategory.FURNITURE);
        product_sofa.setPrice(1000);
        product_sofa.setMemberDiscount(200);
        productRepository.save(product_sofa);


        Product product_wardrobe = new Product();
        product_wardrobe.setName("Wardrobe");
        product_wardrobe.setNumber(1003);
        product_wardrobe.setWeight(200);
        product_wardrobe.setDescription("A nice wardrobe.");
        product_wardrobe.setCategory(Product.ProductCategory.FURNITURE);
        product_wardrobe.setPrice(500);
        product_wardrobe.setMemberDiscount(100);
        productRepository.save(product_wardrobe);

        Product product_rug = new Product();
        product_rug.setName("Rug");
        product_rug.setNumber(1002);
        product_rug.setDescription("Fine woven rug.");
        product_rug.setColour("White");
        product_rug.setCategory(Product.ProductCategory.TEXTILE);
        product_rug.setPrice(800);
        product_rug.setMemberDiscount(100);
        productRepository.save(product_rug);


        Product product_chilli_hot_dog = new Product();
        product_chilli_hot_dog.setName("Chilli Hot dog");
        product_chilli_hot_dog.setDescription("A chilli hot dog");
        product_chilli_hot_dog.setCategory(Product.ProductCategory.HOT_DOG);
        product_chilli_hot_dog.setFlavour("Chilli");
        product_chilli_hot_dog.setPrice(50);
        product_chilli_hot_dog.setMemberDiscount(10);
        productRepository.save(product_chilli_hot_dog);


        Product product_veg_hot_dog = new Product();
        product_veg_hot_dog.setName("Vegetarian Hot dog");
        product_veg_hot_dog.setDescription("A vegetarian hot dog");
        product_veg_hot_dog.setCategory(Product.ProductCategory.HOT_DOG);
        product_veg_hot_dog.setFlavour("Vegetarian");
        product_veg_hot_dog.setPrice(40);
        product_veg_hot_dog.setMemberDiscount(8);
        productRepository.save(product_veg_hot_dog);

        Product product_carpet = new Product();
        product_carpet.setName("Carpet");
        product_carpet.setNumber(1009);
        product_carpet.setDescription("Fine industrial carpet.");
        product_carpet.setColour("Grey");
        product_carpet.setCategory(Product.ProductCategory.TEXTILE);
        product_carpet.setPrice(400);
        product_carpet.setMemberDiscount(50);
        productRepository.save(product_carpet);

        // Example 1: A customer buys a carpet and a chili-flavored hot dog. He is not a member, so he doesn’t get any discount.
        Orders orders1 = new Orders();
        orders1.setCustomer(customer1);
        orders1.setOrderDate(new Date());
        ordersRepository.save(orders1);

        // Carpet
        OrderLine orderLine1 = new OrderLine();
        orderLine1.setOrder(orders1);
        orderLine1.setFinalPrice(product_carpet.getFinalPrice(customer1));
        orderLine1.setPrice(product_carpet.getPrice());
        orderLine1.setFinalPrice(product_carpet.getFinalPrice(customer1));
        orderLine1.setDiscount(product_carpet.getPrice() - product_carpet.getFinalPrice(customer1));
        orderLine1.setProduct(product_carpet);
        orderLineRepository.save(orderLine1);

        // Chili hot dog
        OrderLine orderLine2 = new OrderLine();
        orderLine2.setOrder(orders1);
        orderLine2.setPrice(product_chilli_hot_dog.getPrice());
        orderLine2.setFinalPrice(product_chilli_hot_dog.getFinalPrice(customer1));
        orderLine2.setDiscount(product_chilli_hot_dog.getPrice() - product_chilli_hot_dog.getFinalPrice(customer1));
        orderLine2.setProduct(product_chilli_hot_dog);
        orderLineRepository.save(orderLine2);

        // Example 2: Another customer buys a wardrobe, a rug, and a vegetarian hot dog. She is a member, with furniture and hot dog discounts.
        // So she gets a discount on the wardrobe and the hot dog, but pays normal price for the rug.

        Customer customer2 = new Customer();
        customer2.setName("Kari Nordmann");
        customer2.setEmail("kari.nordman@gmaill.com");
        customer2.setMemberCategory(Customer.MemberCategory.MEMBER);
        customerRepository.save(customer2);


        Orders orders2 = new Orders();
        orders2.setCustomer(customer2);
        orders2.setOrderDate(new Date());
        ordersRepository.save(orders2);

        // Wardrobe
        OrderLine orderLine3 = new OrderLine();
        orderLine3.setOrder(orders2);
        orderLine3.setFinalPrice(product_wardrobe.getFinalPrice(customer2));
        orderLine3.setPrice(product_wardrobe.getPrice());
        orderLine3.setFinalPrice(product_wardrobe.getFinalPrice(customer2));
        orderLine3.setDiscount(product_wardrobe.getPrice() - product_wardrobe.getFinalPrice(customer2));
        orderLine3.setProduct(product_wardrobe);
        orderLineRepository.save(orderLine3);

        // Rug
        OrderLine orderLine4 = new OrderLine();
        orderLine4.setOrder(orders2);
        orderLine4.setFinalPrice(product_rug.getFinalPrice(customer2));
        orderLine4.setPrice(product_rug.getPrice());
        orderLine4.setFinalPrice(product_rug.getFinalPrice(customer2));
        orderLine4.setDiscount(product_rug.getPrice() - product_rug.getFinalPrice(customer2));
        orderLine4.setProduct(product_rug);
        orderLineRepository.save(orderLine4);

        // Vegetar hot dog
        OrderLine orderLine5 = new OrderLine();
        orderLine5.setOrder(orders2);
        orderLine5.setFinalPrice(product_veg_hot_dog.getFinalPrice(customer2));
        orderLine5.setPrice(product_veg_hot_dog.getPrice());
        orderLine5.setFinalPrice(product_veg_hot_dog.getFinalPrice(customer2));
        orderLine5.setDiscount(product_veg_hot_dog.getPrice() - product_veg_hot_dog.getFinalPrice(customer2));
        orderLine5.setProduct(product_veg_hot_dog);
        orderLineRepository.save(orderLine5);


    }
}