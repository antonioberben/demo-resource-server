package io.crpdevs.demo.rs.service;

import io.crpdevs.demo.rs.persistence.repository.RootResourceRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;

import java.util.Optional;

public class RootResourceServiceTest {

    private RootResourceService rootResourceService;
    private RootResourceRepository rootResourceRepositoryMock;

    @Before
    public void setUp() {
        /*rootResourceRepositoryMock = Mockito.mock(RootResourceRepository.class);
        rootResourceService = new RootResourceService(rootResourceRepositoryMock);*/
    }

    @Test
    @DisplayName("Test if true holds")
    public void createSuccessfuly() throws Exception {
        /*when(clientRepositoryMock.findByName(eq("Foo"))).thenReturn(Optional.empty());
        doAnswer(returnsFirstArg()).when(clientRepositoryMock).save(any(Client.class));
        Client client = createClientService.createClient("Foo");
        assertEquals("Foo", client.getName());
        assertNotNull(client.getNumber());*/
    }
}
