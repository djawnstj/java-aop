package camp.nextstep.controller;

import camp.nextstep.service.AppUserService;
import com.interface21.beans.factory.annotation.Autowired;
import com.interface21.context.stereotype.Controller;
import com.interface21.web.bind.annotation.PathVariable;
import com.interface21.web.bind.annotation.RequestMapping;
import com.interface21.web.bind.annotation.RequestMethod;
import com.interface21.web.bind.annotation.RequestParam;
import com.interface21.webmvc.servlet.ModelAndView;
import com.interface21.webmvc.servlet.view.JsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final AppUserService userService;

    @Autowired
    public UserController(final AppUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/api/user/{id}", method = RequestMethod.GET)
    public ModelAndView show(@PathVariable("id") final long id) {
        log.debug("user id : {}", id);

        final var user = userService.findById(id);

        final var modelAndView = new ModelAndView(new JsonView());
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value = "/api/user", method = RequestMethod.GET)
    public ModelAndView show(@RequestParam("account") final String account) {
        log.debug("user account : {}", account);

        final var user = userService.findByAccount(account);

        final var modelAndView = new ModelAndView(new JsonView());
        modelAndView.addObject("user", user);
        return modelAndView;
    }
}
