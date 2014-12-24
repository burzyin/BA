package com.exadel.belarusattractions;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * Developer: Paulau Aliaksandr
 * Created: 12:44 PM, 2/25/13
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-config.xml", "classpath:db-test-config.xml" })
@TransactionConfiguration(defaultRollback = true, transactionManager = "txManager")
public abstract class AbstractTransactionalTestClass extends AbstractTransactionalJUnit4SpringContextTests {

}
