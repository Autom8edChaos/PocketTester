package com.abnamro.apps.pockettester

import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito.*
import java.util.*

/*
This test is more complicated. Because we are not in control over the datamanager, it makes sense to mock this
object. The subscription policy needs the count of the amount of notes from the datamanager, but the datamanager
does not have a direct method to do that. The policy needs to retrieve the amount of topics first and then sum
the amount of notes for each topic. That is the total amount.

Therefore, we need to generate a list with topics and add it to the mocked datamanager. Then we tell the mock to
return a certain count of notes per topic.

The settings object is pending functionality: we do not have a concrete implementation. With mocking we can use
the interface to return whatever amount we want
 */
class SubscriptionPolicyTest {

    private lateinit var topic1: ItemTypeInfo
    private lateinit var topic2: ItemTypeInfo
    private lateinit var subscriptionPolicy: SubscriptionPolicy
    private lateinit var dataManagerMock: IDataManager
    private lateinit var settingsMock: INotifySettings

    @Before
    fun setUp() {
        topic1 = ItemTypeInfo("topicid1", "title1", null)
        topic2 = ItemTypeInfo("topicid2", "title2", null)
        dataManagerMock = mock(IDataManager::class.java)
        var topicList = LinkedList<ItemTypeInfo>().apply {
            add(topic1)
            add(topic2)
        }
        `when`(dataManagerMock.topics).thenReturn(topicList)
        `when`(dataManagerMock.getNoteCount(topic1)).thenReturn(10)
        `when`(dataManagerMock.getNoteCount(topic2)).thenReturn(5)

        settingsMock = mock(INotifySettings::class.java)
        subscriptionPolicy = SubscriptionPolicy(dataManagerMock, settingsMock)
    }

    @Test
    fun givenNotesUnderSubscriptionAmount_whenCheckingIfAddingNewNotesIsAllowed_thenIsAllowed() {
        // Arrange
        `when`(settingsMock.noNewNotesAmount).thenReturn(16)

        // Act
        var isAllowed = subscriptionPolicy.isAllowedToAddNewNotes()

        // Assert
        assertTrue(isAllowed)
    }

    @Test
    fun givenNotesOnSubscriptionAmount_whenCheckingIfAddingNewNotesIsAllowed_thenNotAllowed() {
        // Arrange
        `when`(settingsMock.noNewNotesAmount).thenReturn(15)

        // Act
        var isAllowed = subscriptionPolicy.isAllowedToAddNewNotes()

        // Assert
        assertFalse(isAllowed)
    }

    @Test
    fun givenNotesOverSubscriptionAmount_whenCheckingIfAddingNewNotesIsAllowed_thenNotAllowed() {
        // Arrange
        `when`(settingsMock.noNewNotesAmount).thenReturn(14)

        // Act
        var isAllowed = subscriptionPolicy.isAllowedToAddNewNotes()

        // Assert
        assertFalse(isAllowed)
    }

    @Test
    fun givenNoNotes_whenCheckingIfAddingNewNotesIsAllowed_thenIsAllowed() {
        // Arrange
        `when`(dataManagerMock.getNoteCount(topic1)).thenReturn(0)
        `when`(dataManagerMock.getNoteCount(topic2)).thenReturn(0)
        `when`(settingsMock.noNewNotesAmount).thenReturn(1)

        // Act
        var isAllowed = subscriptionPolicy.isAllowedToAddNewNotes()

        // Assert
        assertTrue(isAllowed)
    }

    @Test
    fun givenNotesUnderWarningAmount_whenCheckingForWarning_thenNoWarning() {
        // Arrange
        `when`(settingsMock.warningAmount).thenReturn(16)

        // Act
        var showWarning = subscriptionPolicy.shouldShowWarning()

        // Assert
        assertFalse(showWarning)
    }

    @Test
    fun givenNotesOnWarningAmount_whenCheckingForWarning_thenWarning() {
        // Arrange
        `when`(settingsMock.warningAmount).thenReturn(15)

        // Act
        var showWarning = subscriptionPolicy.shouldShowWarning()

        // Assert
        assertTrue(showWarning)
    }

    @Test
    fun givenNotesOverWarningAmount_whenCheckingForWarning_thenWarning() {
        // Arrange
        `when`(settingsMock.warningAmount).thenReturn(14)

        // Act
        var showWarning = subscriptionPolicy.shouldShowWarning()

        // Assert
        assertTrue(showWarning)
    }
}