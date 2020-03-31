package com.techland.paypay.annotations;import com.techland.paypay.contracts.PayPayEvent; import com.techland.paypay.contracts.EventSubscriber;import com.techland.paypay.contracts.StateSubscriber;import com.techland.paypay.contracts.Subscriber; import java.util.ArrayList;import java.util.List;import java.util.concurrent.ConcurrentHashMap;import org.springframework.stereotype.Component;import com.techland.paypay.user.subscribers.UserPersistance;import com.techland.paypay.user.subscribers.Emailer;import com.techland.paypay.user.events.UserAddedEvent;import com.techland.paypay.user.events.UserStatusChangedEvent; @Component  public class SubscriberFactory { private UserPersistance userpersistance; private Emailer emailer; private  ConcurrentHashMap<String,List<? extends Subscriber>> finalList = new ConcurrentHashMap<>(); private  List<EventSubscriber> eventlist = new ArrayList<EventSubscriber>(); private  List<StateSubscriber> statelist = new ArrayList<StateSubscriber>();public SubscriberFactory( UserPersistance userpersistance ,Emailer emailer ){ this.userpersistance=userpersistance; this.emailer=emailer;} public  <R extends PayPayEvent>  ConcurrentHashMap<String,List<? extends Subscriber>> getInstance(R event) {  List<EventSubscriber> eventlist = new ArrayList<EventSubscriber>();		 List<StateSubscriber> statelist = new ArrayList<StateSubscriber>(); if (event instanceof UserAddedEvent) { statelist.add(userpersistance); eventlist.add(emailer);}if (event instanceof UserStatusChangedEvent) { statelist.add(userpersistance);} return	getFinalList(eventlist,statelist); }  public  List<EventSubscriber> getAllEventSubscribers() {  if (eventlist.isEmpty()) {  eventlist.add(emailer); } return eventlist; }  public  List<StateSubscriber> getAllStateSubscribers() {  if (statelist.isEmpty()) {  statelist.add(userpersistance); } return statelist; }  private ConcurrentHashMap<String,List<? extends Subscriber>> getFinalList (List<EventSubscriber> eventlist,List<StateSubscriber> statelist) { ConcurrentHashMap<String, List<? extends Subscriber>> finalList = new ConcurrentHashMap<>();  if (eventlist != null) finalList.put("events",eventlist);  if (statelist != null) finalList.put("states",statelist);  return finalList;}} 