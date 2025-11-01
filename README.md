# Orders API

## Endpoints

### 1. Create Order

**POST** `/orders`

Creates a new order.

**Payload:**
```json
{
  "description": "Order description",
  "items": [
    {
      "product": {
        "id": "product-uuid"
      },
      "nrUnits": 2
    }
    // ... more items
  ]
}
```
- `description`: (string) Description of the order.
- `items`: (array) List of items to include in the order.
  - `product.id`: (UUID) The product identifier.
  - `nrUnits`: (number) Number of units for the product.

---

### 2. Update Order

**PATCH** `/orders`

Updates an existing order.

**Payload:**
```json
{
  "id": "order-uuid",
  "description": "Updated order description",
  "items": [
    {
      "product": {
        "id": "product-uuid"
      },
      "nrUnits": 3
    }
    // ... more items
  ]
}
```
- `id`: (UUID) The order identifier to update.
- `description`: (string) New description for the order.
- `items`: (array) Updated list of items for the order.
  - `product.id`: (UUID) The product identifier.
  - `nrUnits`: (number) Number of units for the product.

---
