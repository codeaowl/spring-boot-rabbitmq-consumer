package com.microservice.consumermicroservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import jakarta.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.RedisTemplate;

import com.microservice.consumermicroservice.entity.Product;
import com.microservice.consumermicroservice.facade.ConsumerRepository;

@Service
public class ConsumerService {
	private final String hashReference= "Product";

	private static final Logger LOGGER = LogManager.getLogger(ConsumerService.class);
	public static final String PRODUCT_QUEUE = "product-queue";
	private ConsumerRepository productRepository;

	private final RedisTemplate<String, Product> redisTemplate;

	public ConsumerService(
		ConsumerRepository productRepository, 
		RedisTemplate<String, Product> redisTemplate) {
		this.productRepository = productRepository;
		this.redisTemplate = redisTemplate;
	}

	@RabbitListener(queues = PRODUCT_QUEUE)
	public void receiveMessageAndCreateProduct(Product product) {
		LOGGER.info("Message received :" + product.getProductName());		
		product.setMessageReceived(true);	
		productRepository.save(product);

		LOGGER.info("Message processed...and saved in database");
	}

	public Product delete(int id) {
		Product pd = findById(id);
		if (pd != null) {
			productRepository.delete(pd);
		}
		return pd;
	}

	public List<Product> findAll() {
		List<Product> pdList = new ArrayList<Product>();
		productRepository.findAll().forEach(pdList::add);
		return pdList;
	}

	public Product findById(int id) {
		final String key = "product_" + id;
		final ValueOperations<String, Product> operations = redisTemplate.opsForValue();
		final boolean hasKey = redisTemplate.hasKey(key);

		if(hasKey) {
			final Product product = operations.get(key);
			LOGGER.info("Product from redis cache: " + product.getProductName());
			return product;
		}

		Optional<Product> pd = productRepository.findById(id);
		if(pd.isPresent()) {
			operations.set(key, pd.get(), 10, TimeUnit.SECONDS);
			LOGGER.info("Product from DB: " + pd.get().getProductName());
		}
		return pd.get();
	}

	public Product update(Product pd) {
		return null;//TDOD
	}

}
