package net.media.dbtest;

import net.media.dbtest.Repositories.ConfigRepository;
import net.media.dbtest.Models.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/Configs")
public class ConfigController {

    @Autowired
    private ConfigRepository repository;

    @RequestMapping(value = "/Configs", method = RequestMethod.GET)
    public List<Config> getAllConfigs() {
        return repository.findAll();
    }


}
