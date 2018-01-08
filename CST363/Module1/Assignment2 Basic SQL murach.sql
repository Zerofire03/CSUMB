select vendor_name, vendor_contact_last_name, vendor_contact_first_name 
from vendors
order by vendor_contact_last_name, vendor_contact_first_name;

select concat(vendor_contact_last_name, ', ', vendor_contact_first_name) from vendors
where vendor_contact_last_name regexp '^[ABCE]' 
order by vendor_contact_last_name, vendor_contact_first_name;

select invoice_due_date as 'Due Date', invoice_total as 'Invoice Total', 
   round(.1*invoice_total,2) as '10%', round(1.1*invoice_total,2) as 'plus 10%' 
from invoices
where invoice_total between 500 and 1000
order by invoice_due_date desc;

select invoice_number, 
       invoice_total, 
       payment_total+credit_total as payment_credit_total, 
       invoice_total - payment_total - credit_total as balance_due
from invoices
where invoice_total - payment_total - credit_total > 50
order by balance_due desc
limit 5; 

select invoice_number, 
       invoice_date, 
       invoice_total - payment_total - credit_total as balance_due,
       payment_date
from invoices
where payment_date is null;
	

select date_format(current_date(),'%m-%d-%Y') as 'current_date' ;

select 50000 as starting_principal, 
       round(.065*50000,2) as interest,
       50000+round(.065*50000,2) as principal_plus_interest; 
       
insert into terms (terms_id, terms_description, terms_due_days) 
              values(6, 'Net due 120 days', 120);
              
update terms set terms_description='Net due 125 days', terms_due_days=125
where terms_id=6;

delete from terms where terms_id=6;

insert into invoices 
values (default, 32, 'AX-014-027', '2014-08-01', 434.58, 0, 0, 2, '2014-08-31',null);

insert into invoice_line_items 
values (116, 1, 160, 180.23, 'Hard drive'), 
       (116, 2, 527, 254.35, 'Exchange server update');
       
update invoices 
set credit_total = 0.10*invoice_total, payment_total=0.90*invoice_total
where invoice_id=116;

update vendors
set default_account_number = 403 where vendor_id=44;

update invoices set terms_id = 2
where vendor_id in (select vendor_id from vendors where default_terms_id=2);

select vendor_id from vendors where default_terms_id=2;

select invoice_id, terms_id from invoices 
where vendor_id in (select vendor_id from vendors where default_terms_id=2);

delete from invoice_line_items where invoice_id=116;
delete from invoices where invoice_id=116;
