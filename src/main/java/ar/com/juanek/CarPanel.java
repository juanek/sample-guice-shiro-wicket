package ar.com.juanek;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;


import com.google.inject.Inject;
public class CarPanel extends Panel
{
    private static final long serialVersionUID = 1L;

    @Inject
    private ICarService service;

    /**
     * Constructor
     *
     * @param id
     *            component id
     */
    public CarPanel(String id)
    {
        super(id);
    }


    /** {@inheritDoc} */
    @Override
    public void onInitialize()
    {
        super.onInitialize();

        ListView<Car> carView = new ListView<Car>("carView", new CarListModel())
        {
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<Car> item)
            {
                item.add(new Label("year", new PropertyModel<String>(item.getDefaultModel(), "year")));
                item.add(new Label("make", new PropertyModel<String>(item.getDefaultModel(), "make")));
                item.add(new Label("model", new PropertyModel<String>(item.getDefaultModel(), "model")));
            }
        };
        add(carView);
    }

    /**
     * Model for the car list.
     *
     * @author Jered Myers
     * @since May 15, 2017
     */
    private class CarListModel extends LoadableDetachableModel<List<Car>>
    {
        private static final long serialVersionUID = 1L;

        /** {@inheritDoc} */
        @Override
        protected List<Car> load()
        {
            return service.getCars();
        }

    }
}
