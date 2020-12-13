package de.eurofunk.broker.server.service;

import de.eurofunk.broker.server.domain.MessageDevice;
import de.eurofunk.broker.server.persistance.entity.MessageDeviceEntity;
import de.eurofunk.broker.server.persistance.repository.MessageDeviceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MessageDeviceServiceTest {

    MessageDeviceService messageDeviceService;
    MessageDeviceRepository repository;
    QueueService queueService;

    @BeforeEach
    void setUp() {
        repository = mock(MessageDeviceRepository.class);
        queueService = mock(QueueService.class);

        messageDeviceService = new MessageDeviceService(repository, queueService);
    }

    @Test
    @DisplayName("when registering message device, correct operations are performed")
    public void registerDeviceTest() {
        //when
        messageDeviceService.registerMessageDevice(new MessageDevice("beeper"));

        //then
        verify(repository, times(1)).save(any());
        verify(queueService, times(1)).addQueue("beeper");
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(queueService);
    }

    @Test
    @DisplayName("when removing message device, correct operations are performed")
    public void removeDeviceTest() {
        //when
        messageDeviceService.removeMessageDevice("beeper");

        //then
        verify(repository, times(1)).deleteById("beeper");
        verify(queueService, times(1)).removeQueue("beeper");
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(queueService);
    }

    @Test
    @DisplayName("when getting all devices, correct amount is fetched")
    public void getAllDevicesTest() {
        //given
        MessageDeviceEntity a = createMessageDeviceEntity("a");
        MessageDeviceEntity b = createMessageDeviceEntity("b");
        List<MessageDeviceEntity> mockedList = List.of(a, b);
        when(repository.findAll()).thenReturn(mockedList);

        //when
        List<MessageDevice> resultList = messageDeviceService.getAllMessageDevices();

        //then
        assertEquals(mockedList.size(), resultList.size());
    }

    private MessageDeviceEntity createMessageDeviceEntity(String name) {
        MessageDeviceEntity entity = new MessageDeviceEntity();
        entity.setName(name);
        return entity;
    }
}