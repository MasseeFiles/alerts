package com.safetynet.alerts.service;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.HouseHold;
import com.safetynet.alerts.model.JavaObjectFromJson;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Service
public class EndPoint5Service {
    private final Converter converter;

    @Autowired
    public EndPoint5Service(Converter converter) {
        this.converter = converter;
    }

    //
    public List<HouseHold> getListHouseHold(List<String> addressesCovered) {
        List<HouseHold> listHouseHold = new ArrayList<HouseHold>();
        JavaObjectFromJson data = converter.convertJsonToJavaObject();
        List<Person> listPersonJson = data.getPersons();

//map get put
        //list contains


        List<Person> listPersonCovered = new ArrayList<Person>();   //valeur de retour
//        Iterator<String> iteratorAddressJson = listAddress.iterator();
//
//        while (iteratorAddressJson.hasNext()) {
//            String address = iteratorAddressJson.next();
//            Iterator<Person> iteratorPerson = listPersonJson.iterator();
//
//            while (iteratorPerson.hasNext()) {
//                Person person = iteratorPerson.next();
//                String addressPerson = person.getAddress();
//
//                if (address.equals(addressPerson)) {
//                    listPersonCovered.add(person);
//                }
//
//                return listHouseHold;
//
//            }
//        }
        return null;
    }
}
