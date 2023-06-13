package com.cfm.Yolo.domain.converts;

import com.cfm.Yolo.domain.dto.PersonDto;
import com.cfm.Yolo.domain.model.Person;

import java.util.List;
import java.util.stream.Collectors;

public class PersonConvert {

    // public static PersonDto convertPersonDto(Person person) {
    //     PersonDto personDto = new PersonDto();
    //     personDto.setCode(person.getId());
    //     personDto.setName(person.getName());
    //     personDto.setGender(person.getGender());
    //     personDto.setAvatar(person.getUser().getAvatar());
    //     personDto.setBackground(person.getUser().getBackground());
    //     personDto.setCreatedDate(person.getCreatedDate());
    //     personDto.setUsername(person.getUser().getUsername());
    //     personDto.setSalt(person.getUser().getSalt());
    //     personDto.setPassword(person.getUser().getPassword());
    //     personDto.setOnline(person.getUser().getOnline());
    //     personDto.setUserCreatedDate(person.getUser().getCreatedDate());
    //     return personDto;
    // }

    // public static List<PersonDto> convertPersonDtoList(List<Person> person) {
    //     return person.stream().map(PersonConvert::convertPersonDto).collect(Collectors.toList());
    // }

    // public static Person convertPerson(PersonDto personDto) {
    //     Person person = new Person();
    //     person.setId(personDto.getCode());
    //     person.setName(personDto.getName());
    //     person.setGender(personDto.getGender());
    //     person.getUser().setAvatar(personDto.getAvatar());
    //     person.getUser().setBackground(personDto.getBackground());
    //     person.setCreatedDate(personDto.getCreatedDate());
    //     person.getUser().setUsername(personDto.getUsername());
    //     person.getUser().setSalt(personDto.getSalt());
    //     person.getUser().setPassword(personDto.getPassword());
    //     person.getUser().setOnline(personDto.getOnline());
    //     person.getUser().setCreatedDate(personDto.getUserCreatedDate());
    //     return person;
    // }

    // public static List<Person> convertPersonList(List<PersonDto> personDto) {
    //     return personDto.stream().map(PersonConvert::convertPerson).collect(Collectors.toList());
    // }

}
