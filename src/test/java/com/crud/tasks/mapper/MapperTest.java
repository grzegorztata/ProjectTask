package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class MapperTest {
    @InjectMocks
    private TrelloMapper trelloMapper;

    //Card  Card  Card  Card  Card  Card  Card  Card  Card  Card  Card  Card  Card  Card  Card  Card  Card

    @Test
    public void trelloCardMapper() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name1", "description1", null, "1");
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals("name1", trelloCard.getName());
        assertEquals("description1", trelloCard.getDescription());
        assertEquals(null, trelloCard.getPos());
        assertEquals("1", trelloCard.getListId());
    }

    @Test
    public void trelloCardDtoMapper() {
        //Given
        TrelloCard trelloCard = new TrelloCard("name2", "description2", null, "2");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals("name2", trelloCard.getName());
        assertEquals("description2", trelloCard.getDescription());
        assertEquals(null, trelloCard.getPos());
        assertEquals("2", trelloCard.getListId());
    }

    //List  List  List  List  List  List  List  List  List  List  List  List  List  List  List  List  List  List

    @Test
    public void trelloListDtoMapper() {
        //Given
        List<TrelloList> list = new ArrayList<>();
        list.add(new TrelloList("3", "name3", false));
        list.add(new TrelloList("4", "name4", false));
        list.add(new TrelloList(null, null, false));
        //When
        List<TrelloListDto> listDtos = trelloMapper.mapToListDto(list);
        //Then
        assertEquals(3, listDtos.size());
    }

    @Test
    public void trelloListTestMapper() {
        //Given
        List<TrelloListDto> listDtos = new ArrayList<>();
        listDtos.add(new TrelloListDto("5", "name5", true));
        listDtos.add(new TrelloListDto("6", "name6", true));
        listDtos.add(new TrelloListDto(null, null, true));
        //When
        List<TrelloList> list = trelloMapper.mapToList(listDtos);
        //Then
        assertEquals(3, list.size());
    }

    // Board  Board  Board  Board  Board  Board  Board  Board  Board  Board  Board  Board  Board  Board

    @Test
    public void trelloBoardTestMapper() {
        //Given
        List<TrelloListDto> listDtos = new ArrayList<>();
        listDtos.add(new TrelloListDto("7", "name7", false));
        List<TrelloBoardDto> boardDtos = new ArrayList<>();
        boardDtos.add(new TrelloBoardDto("8", "name8", listDtos));
        boardDtos.add(new TrelloBoardDto("9", "name9", listDtos));
        boardDtos.add(new TrelloBoardDto(null, null, listDtos));

        //When
        List<TrelloBoard> list = trelloMapper.mapToBoards(boardDtos);

        //Then
        assertEquals(3, list.size());
    }

    @Test
    public void trelloBoardDtoMapper() {
        //Given
        List<TrelloList> list = new ArrayList<>();
        list.add(new TrelloList("10", "name10", false));
        List<TrelloBoard> list1 = new ArrayList<>();
        list1.add(new TrelloBoard("11", "name11", list));
        list1.add(new TrelloBoard("12", "name12", list));
        list1.add(new TrelloBoard(null, null, list));

        //When
        List<TrelloBoardDto> trelloBoard = trelloMapper.mapToBoardsDto(list1);

        //Then
        assertEquals(3, trelloBoard.size());
    }
}

