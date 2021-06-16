create table cs_agency
(
    id          bigint not null auto_increment,
    agency_name varchar(255),
    primary key (id)
);
create table cs_agent
(
    id         bigint not null auto_increment,
    agent_name varchar(255),
    shop_id    bigint,
    primary key (id)
);
create table cs_coffee
(
    id          bigint not null auto_increment,
    cost        bigint,
    description varchar(255),
    name        varchar(255),
    shop_id     bigint,
    primary key (id)
);
create table cs_order
(
    id        bigint not null auto_increment,
    cost      bigint,
    agent_id  bigint,
    coffee_id bigint,
    user_id   bigint,
    primary key (id)
);
create table cs_user
(
    id       bigint not null auto_increment,
    password varchar(255),
    username varchar(255),
    primary key (id)
);
alter table cs_user
    add constraint UK_q3ap1j0ufkqqfc5tkrravwter unique (username);
alter table cs_agent
    add constraint FKmld740it0vuygnvt9j8fmmqnl foreign key (shop_id) references cs_agency (id);
alter table cs_coffee
    add constraint FKdlq5l1itjsjfqvr8mswkfevx4 foreign key (shop_id) references cs_agency (id);
alter table cs_order
    add constraint FKix3jq1dj41re0nhey021l8w69 foreign key (agent_id) references cs_agent (id);
alter table cs_order
    add constraint FKql5xm46ydf58mvxjxd97er2sr foreign key (coffee_id) references cs_coffee (id);
alter table cs_order
    add constraint FK3axic2oo1cuee7dk0pw3b5dnx foreign key (user_id) references cs_user (id);
