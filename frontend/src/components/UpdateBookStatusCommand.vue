<template>
    <v-card outlined>
        <v-card-title>
            UpdateBookStatus
        </v-card-title>

        <v-card-text>
            <String label="BookId" v-model="value.bookId" :editMode="editMode"/>
            <BookStatus offline label="Status" v-model="value.status" :editMode="editMode" @change="change"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="updateBookStatus"
            >
                UpdateBookStatus
            </v-btn>
            
            <v-btn
                    color="primary"
                    text
                    @click="close"
            >
                Close
            </v-btn>
        </v-card-actions>
    </v-card>

</template>

<script>
   
    export default {
        name: 'UpdateBookStatusCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.bookId = '';
            this.value.status = {};
        },
        watch: {
        },
        methods: {
            updateBookStatus() {
                this.$emit('updateBookStatus', this.value);
            },
            close() {
                this.$emit('closeDialog');
            },
            change() {
                this.$emit('input', this.value);
            },
        }
    }
</script>

