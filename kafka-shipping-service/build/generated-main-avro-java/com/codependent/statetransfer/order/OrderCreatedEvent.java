/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.codependent.statetransfer.order;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class OrderCreatedEvent extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 677775922820542002L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"OrderCreatedEvent\",\"namespace\":\"com.codependent.statetransfer.order\",\"fields\":[{\"name\":\"id\",\"type\":\"int\"},{\"name\":\"productId\",\"type\":\"int\"},{\"name\":\"customerId\",\"type\":\"int\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<OrderCreatedEvent> ENCODER =
      new BinaryMessageEncoder<OrderCreatedEvent>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<OrderCreatedEvent> DECODER =
      new BinaryMessageDecoder<OrderCreatedEvent>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<OrderCreatedEvent> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<OrderCreatedEvent> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<OrderCreatedEvent>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this OrderCreatedEvent to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a OrderCreatedEvent from a ByteBuffer. */
  public static OrderCreatedEvent fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public int id;
  @Deprecated public int productId;
  @Deprecated public int customerId;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public OrderCreatedEvent() {}

  /**
   * All-args constructor.
   * @param id The new value for id
   * @param productId The new value for productId
   * @param customerId The new value for customerId
   */
  public OrderCreatedEvent(java.lang.Integer id, java.lang.Integer productId, java.lang.Integer customerId) {
    this.id = id;
    this.productId = productId;
    this.customerId = customerId;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return id;
    case 1: return productId;
    case 2: return customerId;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: id = (java.lang.Integer)value$; break;
    case 1: productId = (java.lang.Integer)value$; break;
    case 2: customerId = (java.lang.Integer)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'id' field.
   * @return The value of the 'id' field.
   */
  public java.lang.Integer getId() {
    return id;
  }

  /**
   * Sets the value of the 'id' field.
   * @param value the value to set.
   */
  public void setId(java.lang.Integer value) {
    this.id = value;
  }

  /**
   * Gets the value of the 'productId' field.
   * @return The value of the 'productId' field.
   */
  public java.lang.Integer getProductId() {
    return productId;
  }

  /**
   * Sets the value of the 'productId' field.
   * @param value the value to set.
   */
  public void setProductId(java.lang.Integer value) {
    this.productId = value;
  }

  /**
   * Gets the value of the 'customerId' field.
   * @return The value of the 'customerId' field.
   */
  public java.lang.Integer getCustomerId() {
    return customerId;
  }

  /**
   * Sets the value of the 'customerId' field.
   * @param value the value to set.
   */
  public void setCustomerId(java.lang.Integer value) {
    this.customerId = value;
  }

  /**
   * Creates a new OrderCreatedEvent RecordBuilder.
   * @return A new OrderCreatedEvent RecordBuilder
   */
  public static com.codependent.statetransfer.order.OrderCreatedEvent.Builder newBuilder() {
    return new com.codependent.statetransfer.order.OrderCreatedEvent.Builder();
  }

  /**
   * Creates a new OrderCreatedEvent RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new OrderCreatedEvent RecordBuilder
   */
  public static com.codependent.statetransfer.order.OrderCreatedEvent.Builder newBuilder(com.codependent.statetransfer.order.OrderCreatedEvent.Builder other) {
    return new com.codependent.statetransfer.order.OrderCreatedEvent.Builder(other);
  }

  /**
   * Creates a new OrderCreatedEvent RecordBuilder by copying an existing OrderCreatedEvent instance.
   * @param other The existing instance to copy.
   * @return A new OrderCreatedEvent RecordBuilder
   */
  public static com.codependent.statetransfer.order.OrderCreatedEvent.Builder newBuilder(com.codependent.statetransfer.order.OrderCreatedEvent other) {
    return new com.codependent.statetransfer.order.OrderCreatedEvent.Builder(other);
  }

  /**
   * RecordBuilder for OrderCreatedEvent instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<OrderCreatedEvent>
    implements org.apache.avro.data.RecordBuilder<OrderCreatedEvent> {

    private int id;
    private int productId;
    private int customerId;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.codependent.statetransfer.order.OrderCreatedEvent.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.productId)) {
        this.productId = data().deepCopy(fields()[1].schema(), other.productId);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.customerId)) {
        this.customerId = data().deepCopy(fields()[2].schema(), other.customerId);
        fieldSetFlags()[2] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing OrderCreatedEvent instance
     * @param other The existing instance to copy.
     */
    private Builder(com.codependent.statetransfer.order.OrderCreatedEvent other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.productId)) {
        this.productId = data().deepCopy(fields()[1].schema(), other.productId);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.customerId)) {
        this.customerId = data().deepCopy(fields()[2].schema(), other.customerId);
        fieldSetFlags()[2] = true;
      }
    }

    /**
      * Gets the value of the 'id' field.
      * @return The value.
      */
    public java.lang.Integer getId() {
      return id;
    }

    /**
      * Sets the value of the 'id' field.
      * @param value The value of 'id'.
      * @return This builder.
      */
    public com.codependent.statetransfer.order.OrderCreatedEvent.Builder setId(int value) {
      validate(fields()[0], value);
      this.id = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'id' field has been set.
      * @return True if the 'id' field has been set, false otherwise.
      */
    public boolean hasId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'id' field.
      * @return This builder.
      */
    public com.codependent.statetransfer.order.OrderCreatedEvent.Builder clearId() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'productId' field.
      * @return The value.
      */
    public java.lang.Integer getProductId() {
      return productId;
    }

    /**
      * Sets the value of the 'productId' field.
      * @param value The value of 'productId'.
      * @return This builder.
      */
    public com.codependent.statetransfer.order.OrderCreatedEvent.Builder setProductId(int value) {
      validate(fields()[1], value);
      this.productId = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'productId' field has been set.
      * @return True if the 'productId' field has been set, false otherwise.
      */
    public boolean hasProductId() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'productId' field.
      * @return This builder.
      */
    public com.codependent.statetransfer.order.OrderCreatedEvent.Builder clearProductId() {
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'customerId' field.
      * @return The value.
      */
    public java.lang.Integer getCustomerId() {
      return customerId;
    }

    /**
      * Sets the value of the 'customerId' field.
      * @param value The value of 'customerId'.
      * @return This builder.
      */
    public com.codependent.statetransfer.order.OrderCreatedEvent.Builder setCustomerId(int value) {
      validate(fields()[2], value);
      this.customerId = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'customerId' field has been set.
      * @return True if the 'customerId' field has been set, false otherwise.
      */
    public boolean hasCustomerId() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'customerId' field.
      * @return This builder.
      */
    public com.codependent.statetransfer.order.OrderCreatedEvent.Builder clearCustomerId() {
      fieldSetFlags()[2] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public OrderCreatedEvent build() {
      try {
        OrderCreatedEvent record = new OrderCreatedEvent();
        record.id = fieldSetFlags()[0] ? this.id : (java.lang.Integer) defaultValue(fields()[0]);
        record.productId = fieldSetFlags()[1] ? this.productId : (java.lang.Integer) defaultValue(fields()[1]);
        record.customerId = fieldSetFlags()[2] ? this.customerId : (java.lang.Integer) defaultValue(fields()[2]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<OrderCreatedEvent>
    WRITER$ = (org.apache.avro.io.DatumWriter<OrderCreatedEvent>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<OrderCreatedEvent>
    READER$ = (org.apache.avro.io.DatumReader<OrderCreatedEvent>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}