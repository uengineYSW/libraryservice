<template>
    <v-card outlined style="border-radius: 0; margin-top:-10px; padding-bottom:10px;">
        <v-card-title>
            도서 상세 정보
        </v-card-title>
    
        <v-card-text style="margin-top:-30px;">
            <v-row no-gutters style="margin: 15px 0px -30px -15px;">
                <v-col style="max-width:140px;">
                    <String class="attributes-list" label="BookId" v-model="value.parameters.bookId" :editMode="editMode"/>
                </v-col>
                <v-col style="max-width:140px;">
                    <String class="attributes-list" label="Title" v-model="value.parameters.title" :editMode="editMode"/>
                </v-col>
                <v-col style="max-width:140px;">
                    <ISBN offline label="Isbn" v-model="value.parameters.isbn" :editMode="editMode" @change="change"/>
                </v-col>
                <v-col style="max-width:140px;">
                    <String class="attributes-list" label="Author" v-model="value.parameters.author" :editMode="editMode"/>
                </v-col>
                <v-col style="max-width:140px;">
                    <String class="attributes-list" label="Publisher" v-model="value.parameters.publisher" :editMode="editMode"/>
                </v-col>
                <v-col style="max-width:140px;">
                    <BookCategory offline label="Category" v-model="value.parameters.category" :editMode="editMode" @change="change"/>
                </v-col>
                <v-col style="max-width:140px;">
                    <BookStatus offline label="Status" v-model="value.parameters.status" :editMode="editMode" @change="change"/>
                </v-col>
                <v-col>
                    <v-btn class="gs-query-search-btn contrast-primary-text"
                        @click="search"
                        small
                        color="primary"
                    >
                        <v-icon small>mdi-magnify</v-icon>검색
                    </v-btn>
                </v-col>
            </v-row>
        </v-card-text>
    </v-card>
</template>

<script>
    export default {
        name: 'BookDetails',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {
                apiPath: 'books/search/findByBookDetails',
                parameters: {}
            },
        }),
        created() {
        },
        watch: {
        },
        methods: {
            search() {
                let search = null;
                search = this.value;
                this.$emit('search', search);
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
<style>
.attributes-list{
    margin-left: 15px;
}
</style>

