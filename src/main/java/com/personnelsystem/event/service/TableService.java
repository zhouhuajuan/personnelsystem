package com.personnelsystem.event.service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface TableService {

    void downloadTable(Integer id, HttpServletResponse response) throws IOException;
}
