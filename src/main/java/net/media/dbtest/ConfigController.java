package net.media.dbtest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class ConfigController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

}
