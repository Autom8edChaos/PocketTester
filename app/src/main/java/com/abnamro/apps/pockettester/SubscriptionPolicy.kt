package com.abnamro.apps.pockettester

/*
The SubscriptionPolicy object manages the policy if new notes can be added or that a subscription must be
purchased. The amount of notes that can be created without a subscription is managed in the settings. By adding
the INotifySettings interface to the settings, you can use the general settings object to manage this.

This showcases an object with dependency injection to acquire inversion of control. This is very valuable for testing,
because now, the dependencies can be mocked.

Why do you want that?
For example: You have no control over the DataManager and the amount of notes that is in there, so you want to mock it.
On the other hand, the GeneralSettings object is pending functionality, so there exist no concrete implementation.
Because the dependencies are injected by their interface, they are easily mockable and therefore testable.

For the mocking example, please look at the SubscriptionPolicyTest
 */

class SubscriptionPolicy constructor(val dataManager: IDataManager, val notifySettings: INotifySettings) {

    fun isAllowedToAddNewNotes() = getNotesCount() < notifySettings.noNewNotesAmount
    fun shouldShowWarning() = getNotesCount() >= notifySettings.warningAmount

    fun getNotesCount() = dataManager.topics.sumBy { item -> dataManager.getNoteCount(item) }
}