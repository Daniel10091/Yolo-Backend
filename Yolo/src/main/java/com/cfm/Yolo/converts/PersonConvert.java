package com.cfm.Yolo.converts;

import com.cfm.Yolo.dto.PersonDto;
import com.cfm.Yolo.model.Person;

import java.util.List;
import java.util.stream.Collectors;

public class PersonConvert {

    public static PersonDto convertPersonDto(Person person) {
        PersonDto personDto = new PersonDto();
        personDto.setCode(person.getId());
        personDto.setName(person.getName());
        personDto.setGender(person.getGender());
        personDto.setAvatar(person.getAvatar());
        personDto.setBackground(person.getBackground());
        return personDto;
    }

    public static List<PersonDto> convertPersonDtoList(List<Person> person) {
        return person.stream().map(PersonConvert::convertPersonDto).collect(Collectors.toList());
    }

    public static Person convertPerson (PersonDto personDto) {
        Person person = new Person();
        person.setId(personDto.getCode());
        person.setName(personDto.getName());
        person.setGender(personDto.getGender());
        person.setAvatar(personDto.getAvatar());
        person.setBackground(personDto.getBackground());
        return person;
    }

    public static List<Person> convertPersonList(List<PersonDto> personDto) {
        return personDto.stream().map(PersonConvert::convertPerson).collect(Collectors.toList());
    }

}
